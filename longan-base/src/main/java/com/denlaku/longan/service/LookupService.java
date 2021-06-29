package com.denlaku.longan.service;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.dao.LookupDao;
import com.denlaku.longan.vo.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianx
 */
@Service
public class LookupService {

    @Autowired
    private LookupDao lookupDao;

    public String nextId() {
        return SnowFlakeService.nextId(BizModule.LOOKUP);
    }

    /**
     * 查询lookup
     * @param query 查询参数
     * @return lookup列表
     */
    public List<Lookup> list(Lookup query) {
        return lookupDao.list(query);
    }

    /**
     * 获取lookup
     * @param query
     * @return Lookup
     */
    public Lookup get(Lookup query) {
        return lookupDao.get(query);
    }

    /**
     * 新增lookup
     * @param lookup
     * @return 新增行数
     */
    public Integer add(Lookup lookup) {
        lookup.setId(nextId());
        return lookupDao.add(lookup);
    }

    /**
     * 更新lookup
     * @param lookup lookup
     * @return 更新行数
     */
    public Integer update(Lookup lookup) {
        return lookupDao.update(lookup);
    }

    /**
     * 删除lookup
     * @param lookup 参数
     * @return 删除行数
     */
    public Integer delete(Lookup lookup) {
        return lookupDao.delete(lookup);
    }
}
