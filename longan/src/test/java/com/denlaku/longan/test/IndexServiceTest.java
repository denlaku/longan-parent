package com.denlaku.longan.test;

import com.denlaku.longan.LonganApplication;
import com.denlaku.longan.service.IndexHolder;
import com.denlaku.longan.service.IndexQuery;
import com.denlaku.longan.service.IndexService;
import com.denlaku.longan.util.Lists;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexableField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LonganApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexServiceTest {

    @Autowired
    private IndexService indexService;

    @Test
    public void test() {
        String namespace = "default";
        String biz = "test";
        IndexHolder holder = new IndexHolder();
        holder.setNamespace(namespace);
        holder.setBiz(biz);
        Document document = new Document();
        FieldType nameFieldType = new FieldType();
        nameFieldType.setTokenized(true);
        nameFieldType.setStored(true);
        nameFieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        Field nameField = new Field("name", "这是最好的时代", nameFieldType);
        document.add(nameField);
        holder.addDocument(document);
        indexService.index(holder);
    }

    @Test
    public void testSearch() {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setNamespace("default");
        indexQuery.setBiz("test");
        indexQuery.setTop(10);
        indexQuery.setFields(Lists.of("name"));
        indexQuery.setKeyword("时代");
        List<Document> documents = indexService.search(indexQuery);
        System.out.println("===============: " + documents.size());
        documents.forEach(document -> {
            List<IndexableField> fields = document.getFields();
            fields.forEach(field -> {
                String value = field.stringValue();
                System.out.println(value);
            });
        });
    }

}
