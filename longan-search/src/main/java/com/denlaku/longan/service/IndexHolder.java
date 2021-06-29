package com.denlaku.longan.service;

import com.denlaku.longan.util.Lists;
import lombok.Data;
import org.apache.lucene.document.Document;

import java.util.List;

/**
 * @author tianx
 */
@Data
public class IndexHolder {
    private String namespace;
    private String biz;
    private List<Document> documents;

    public void addDocument(Document document) {
        if (documents == null) {
            documents = Lists.ofSize(8);
        }
        documents.add(document);
    }
}
