package com.denlaku.longan.service;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.dao.I18nDao;
import com.denlaku.longan.vo.I18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianx
 */
@Service
public class I18nService {

    @Autowired
    private I18nDao i18nDao;

    public String nextId() {
        return SnowFlakeService.nextId(BizModule.I18N);
    }

    /**
     * 新增国际化
     * @param i18n
     * @return 新增条数
     */
    public Integer add(I18n i18n) {
        i18n.setId(nextId());
        return i18nDao.add(i18n);
    }

    /**
     * 删除国际化
     * @param query
     * @return 删除条数
     */
    public Integer delete(I18n query) {
        return i18nDao.delete(query);
    }

    /**
     * 更新国际化
     * @param i18n
     * @return 更新条数
     */
    public Integer update(I18n i18n) {
        return i18nDao.update(i18n);
    }

    /**
     * 查询国际化
     * @param query
     * @return i18n列表
     */
    public List<I18n> list(I18n query) {
        return i18nDao.list(query);
    }

    /**
     * 获取国际化
     * @param query
     * @return i18n
     */
    public I18n get(I18n query) {
        return i18nDao.get(query);
    }

}
