package com.denlaku.longan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherManager;
import org.lionsoul.jcseg.dic.ADictionary;

import java.io.IOException;

/**
 * @author tianx
 */
@Slf4j
public class IndexComponent {

    private SearcherManager searcherManager;
    private IndexWriter indexWriter;
    private Analyzer analyzer;
    private ADictionary dictionary;

    public IndexSearcher getIndexSearcher() {
        try {
            return searcherManager.acquire();
        } catch (IOException e) {
            log.error("Failed to index searcher", e);
        }
        return null;
    }

    public IndexWriter getIndexWriter() {
        return this.indexWriter;
    }

    public void setSearcherManager(SearcherManager searcherManager) {
        this.searcherManager = searcherManager;
    }

    public void setIndexWriter(IndexWriter indexWriter) {
        this.indexWriter = indexWriter;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public Analyzer getAnalyzer() {
        return this.analyzer;
    }

    public void setDictionary(ADictionary dictionary) {
        this.dictionary = dictionary;
    }

    public ADictionary getDictionary() {
        return this.dictionary;
    }

}
