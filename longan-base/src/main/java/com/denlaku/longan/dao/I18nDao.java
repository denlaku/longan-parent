package com.denlaku.longan.dao;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.I18n;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianx
 */
@Repository
public interface I18nDao {

    /**
     * 新增国际化
     * @param i18n
     * @return 新增条数
     */
    @VarOp
    Integer add(I18n i18n);

    /**
     * 删除国际化
     * @param i18n
     * @return 删除条数
     */
    Integer delete(I18n i18n);

    /**
     * 更新国际化
     * @param i18n
     * @return 更新条数
     */
    @VarOp
    Integer update(I18n i18n);

    /**
     * 查询国际化
     * @param query
     * @return i18n列表
     */
    List<I18n> list(I18n query);

    /**
     * 获取国际化
     * @param query
     * @return i18n
     */
    I18n get(I18n query);
}
