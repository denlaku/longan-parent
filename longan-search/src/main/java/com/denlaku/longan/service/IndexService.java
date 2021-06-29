package com.denlaku.longan.service;

import com.denlaku.longan.config.ConfigProperties;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Strings;
import com.denlaku.longan.vo.WordItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.lionsoul.jcseg.ISegment;
import org.lionsoul.jcseg.IWord;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.dic.ADictionary;
import org.lionsoul.jcseg.dic.DictionaryFactory;
import org.lionsoul.jcseg.dic.ILexicon;
import org.lionsoul.jcseg.segmenter.SegmenterConfig;
import org.lionsoul.jcseg.segmenter.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tianx
 */
@Service
@Slf4j
public class IndexService {

    private static final String INDEX_PATH = "indies";
    private Object componentMonitor = new Object();
    private Object dictMonitor = new Object();
    private Map<String, IndexComponent> indexComponentMap = new ConcurrentHashMap<>();
    private Map<String, ADictionary> dictionaryMap = new ConcurrentHashMap<>();

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private WordItemService wordItemService;

    public void index(IndexHolder holder) {
        String namespace = holder.getNamespace();
        String biz = holder.getBiz();
        List<Document> documents = holder.getDocuments();
        if (Strings.isAnyEmpty(namespace, biz) || Lists.isEmpty(documents)) {
            return;
        }
        IndexComponent indexComponent = getOrInitIndexComponent(namespace, biz);
        IndexWriter indexWriter = indexComponent.getIndexWriter();
        try {
            for (Document document : documents) {
                indexWriter.addDocument(document);
            }
            indexWriter.flush();
            indexWriter.commit();
        } catch (IOException e) {
            log.error("add document error", e);
        }
    }

    public void delete(IndexQuery indexQuery) {
        try {
            String namespace = indexQuery.getNamespace();
            String biz = indexQuery.getBiz();
            List<String> fields = indexQuery.getFields();
            if (Strings.isAnyEmpty(namespace, biz) || Lists.isEmpty(fields)) {
                return;
            }
            String keyword = indexQuery.getKeyword();
            IndexComponent indexComponent = getOrInitIndexComponent(namespace, biz);
            IndexWriter indexWriter = indexComponent.getIndexWriter();

            TermQuery query = new TermQuery(new Term(fields.get(0), new BytesRef(keyword.getBytes())));
            indexWriter.deleteDocuments(query);
        } catch (Exception e) {
            log.error("delete index error", e);
        }
    }

    public List<Document> search(IndexQuery indexQuery) {
        try {
            String namespace = indexQuery.getNamespace();
            String biz = indexQuery.getBiz();
            int top = indexQuery.getTop() <= 0 ? 10 : indexQuery.getTop();
            String keyword = indexQuery.getKeyword();
            List<String> fields = indexQuery.getFields();
            if (Strings.isAnyEmpty(namespace, biz) || Lists.isEmpty(fields)) {
                return Lists.empty();
            }

            IndexComponent indexComponent = getOrInitIndexComponent(namespace, biz);
            IndexSearcher indexSearcher = indexComponent.getIndexSearcher();

            Analyzer analyzer = indexComponent.getAnalyzer();
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields.toArray(new String[] {}), analyzer);
            Query query = parser.parse(keyword);
            TopDocs search = indexSearcher.search(query, top);

            ScoreDoc[] scoreDocs = search.scoreDocs;
            List<Document> documents = Lists.ofSize(scoreDocs.length);
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                documents.add(document);
            }
            return documents;
        } catch (Exception e) {
            log.error("search error", e);
        }
        return Lists.empty();
    }


    private IndexComponent getOrInitIndexComponent(String namespace, String biz) {
        String key = String.join("-", namespace, biz);
        IndexComponent indexComponent = indexComponentMap.get(key);
        if (indexComponent != null) {
            return indexComponent;
        }
        synchronized (componentMonitor) {
            indexComponent = indexComponentMap.get(key);
            if (indexComponent != null) {
                return indexComponent;
            }
            indexComponent = initIndexComponent(namespace, biz);
            if (indexComponent != null) {
                indexComponentMap.put(key, indexComponent);
            }
        }
        return indexComponent;
    }

    private IndexComponent initIndexComponent(String namespace, String biz) {
        try {
            ADictionary defaultDictionary = getOrInitDictionary(namespace);
            SegmenterConfig segmenterConfig = defaultDictionary.getConfig();
            loadWordItems(namespace, defaultDictionary);

            //使用标准的分词算法对原始记录表进行拆分
            Analyzer analyzer = new JcsegAnalyzer(ISegment.COMPLEX, segmenterConfig, defaultDictionary);

            //创建IndexWriter对象
            Path path = Paths.get(configProperties.getDir(), INDEX_PATH, namespace, biz);
            Directory directory = FSDirectory.open(path);

            IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));

            SearcherManager sm = new SearcherManager(indexWriter, true, true, null);

            ControlledRealTimeReopenThread thread = new ControlledRealTimeReopenThread(indexWriter, sm, 60, 1);

            thread.setDaemon(true);
            thread.setName(String.join(",", "ControlledRealTimeReopenThread", namespace, biz));
            thread.start();

            IndexComponent indexComponent = new IndexComponent();
            indexComponent.setSearcherManager(sm);
            indexComponent.setIndexWriter(indexWriter);
            indexComponent.setAnalyzer(analyzer);
            indexComponent.setDictionary(defaultDictionary);

            return indexComponent;
        } catch (Exception e) {
            log.error("Failed to init index component", e);
        }
        return null;
    }

    private ADictionary getOrInitDictionary(String namespace) {
        ADictionary dictionary = dictionaryMap.get(namespace);
        if (dictionary != null) {
            return dictionary;
        }
        synchronized (dictMonitor) {
            dictionary = dictionaryMap.get(namespace);
            if (dictionary == null) {
                SegmenterConfig segmenterConfig = new SegmenterConfig();
                dictionary = DictionaryFactory.createDefaultDictionary(segmenterConfig);
                dictionaryMap.put(namespace, dictionary);
            }
        }
        return dictionary;
    }

    public void loadWordItems(String namespace) {
        try {
            ADictionary dictionary = dictionaryMap.get(namespace);
            loadWordItems(namespace, dictionary);
        } catch (Exception e) {
            log.error("Failed to load word item", e);
        }
    }

    private void loadWordItems(String namespace, ADictionary dictionary) {
        WordItem query = new WordItem();
        query.setTenantId(namespace);
        List<WordItem> wordItems = wordItemService.list(query);
        if (Lists.isNotEmpty(wordItems)) {
            wordItems.forEach(item -> {
                String text = item.getText();
                IWord word = new Word(text, ILexicon.CJK_WORD);
                dictionary.add(ILexicon.CJK_WORD, word);
            });
        }
    }
}
