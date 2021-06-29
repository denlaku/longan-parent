package com.denlaku.longan.service;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.dao.BizTagDao;
import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.vo.BizTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianx
 */
@Service
public class BizTagService {

    @Autowired
    private BizTagDao bizTagDao;

    public String nextId() {
        return SnowFlakeService.nextId(BizModule.BIZ_TAG);
    }

    /**
     * 添加tag
     * @param bizTag tag
     * @return 添加行数
     */
    public Integer add(BizTag bizTag) {
        bizTag.setId(nextId());
        return bizTagDao.add(bizTag);
    }

    /**
     * 删除tag
     * @param query 参数
     * @return 删除行数
     */
    public Integer delete(BizTagQuery query) {
        return bizTagDao.delete(query);
    }

    /**
     * 查询tag
     * @param query 参数
     * @return tag列表
     */
    public List<BizTag> list(BizTagQuery query) {
        return bizTagDao.list(query);
    }

}
