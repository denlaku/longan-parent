package com.denlaku.longan.service;

import com.denlaku.longan.dao.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianx
 */
@Service
public class SequenceService {

    @Autowired
    private SequenceDao sequenceDao;

    public long nextVal() {
        return sequenceDao.nextVal();
    }


}
