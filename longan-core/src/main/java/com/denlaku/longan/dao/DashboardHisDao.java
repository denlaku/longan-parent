package com.denlaku.longan.dao;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.qo.DashboardHisQuery;
import com.denlaku.longan.vo.DashboardHis;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianx
 */
@Repository
public interface DashboardHisDao {

    /**
     * 添加仪表板历史记录
     * @param dashboardHis
     * @return
     */
    @VarOp
    Integer add(DashboardHis dashboardHis);

    /**
     * 删除仪表板历史记录
     * @param query
     * @return
     */
    Integer delete(DashboardHisQuery query);

    /**
     * 获取历史记录
     * @param id
     * @return
     */
    DashboardHis get(String id);

    /**
     *
     * @param query
     * @return
     */
    List<DashboardHis> list(DashboardHisQuery query);

}
