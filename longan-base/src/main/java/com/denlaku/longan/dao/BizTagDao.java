package com.denlaku.longan.dao;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.vo.BizTag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianx
 */
@Repository
public interface BizTagDao {

    /**
     * 添加tag
     * @param bizTag tag
     * @return 添加行数
     */
    @VarOp
    Integer add(BizTag bizTag);

    /**
     * 删除tag
     * @param query 参数
     * @return 删除行数
     */
    Integer delete(BizTagQuery query);

    /**
     * 查询tag
     * @param query 参数
     * @return tag列表
     */
    List<BizTag> list(BizTagQuery query);

}
