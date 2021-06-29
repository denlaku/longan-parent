package com.denlaku.longan.service;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.dao.LookupItemDao;
import com.denlaku.longan.vo.LookupItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianx
 */
@Service
public class LookupItemService {

    @Autowired
    private LookupItemDao lookupItemDao;

    public String nextId() {
        return SnowFlakeService.nextId(BizModule.LOOKUP_ITEM);
    }

    /**
     * 查询lookupItem
     * @param query 查询参数
     * @return lookupItem列表
     */
    public List<LookupItem> list(LookupItem query) {
        return lookupItemDao.list(query);
    }

    /**
     * 新增lookupItem
     * @param lookupItem lookupItem
     * @return 新增行数
     */
    public Integer add(LookupItem lookupItem) {
        lookupItem.setId(this.nextId());
        return lookupItemDao.add(lookupItem);
    }

    /**
     * 根据tenantId和lookupCode删除lookupItem
     * @param query 参数
     * @return 删除行数
     */
    public Integer delete(LookupItem query) {
        return lookupItemDao.delete(query);
    }

}
