package com.denlaku.longan.dao;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.LookupItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianx
 */
@Repository
public interface LookupItemDao {

    /**
     * 查询lookupItem
     * @param query 查询参数
     * @return lookupItem列表
     */
    List<LookupItem> list(LookupItem query);

    /**
     * 新增lookupItem
     * @param lookupItem lookupItem
     * @return 新增行数
     */
    @VarOp
    Integer add(LookupItem lookupItem);

    /**
     * 根据tenantId和lookupCode删除lookupItem
     * @param query 参数
     * @return 删除行数
     */
    @VarOp
    Integer delete(LookupItem query);
}
