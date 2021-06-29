package com.denlaku.longan.dao;

import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface SequenceDao {
    /**
     *
     * @return
     */
    Long nextVal();

    /**
     *
     * @param seqName
     * @return
     */
    Integer init(String seqName);
}
