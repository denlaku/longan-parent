package com.denlaku.longan.dao;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Lookup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianx
 */
@Repository
public interface LookupDao {

    /**
     * 查询lookup
     * @param query 查询参数
     * @return lookup列表
     */
    List<Lookup> list(Lookup query);

    /**
     * 获取lookup
     * @param query
     * @return Lookup
     */
    Lookup get(Lookup query);

    /**
     * 新增lookup
     * @param lookup
     * @return 新增行数
     */
    @VarOp
    Integer add(Lookup lookup);

    /**
     * 更新lookup
     * @param lookup lookup
     * @return 更新行数
     */
    @VarOp
    Integer update(Lookup lookup);

    /**
     * 删除lookup
     * @param lookup 参数
     * @return 删除行数
     */
    Integer delete(Lookup lookup);
}
