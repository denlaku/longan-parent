package com.denlaku.longan.service;

import lombok.Data;

import java.util.List;

/**
 * @author tianx
 */
@Data
public class IndexQuery {
    private String keyword;
    private String namespace;
    private String biz;
    private int top;
    private List<String> fields;
}
