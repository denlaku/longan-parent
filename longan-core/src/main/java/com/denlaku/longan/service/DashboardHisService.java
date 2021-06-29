package com.denlaku.longan.service;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.dao.DashboardHisDao;
import com.denlaku.longan.qo.DashboardHisQuery;
import com.denlaku.longan.vo.DashboardHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianx
 */
@Service
public class DashboardHisService {

    @Autowired
    private DashboardHisDao dashboardHisDao;

    public String nextId() {
        return SnowFlakeService.nextId(BizModule.DASHBOARD_HIS);
    }

    /**
     * 添加仪表板历史记录
     * @param dashboardHis
     * @return
     */
    public Integer add(DashboardHis dashboardHis) {
        if (dashboardHis == null) {
            return 0;
        }
        dashboardHis.setId(nextId());
        return dashboardHisDao.add(dashboardHis);
    }

    /**
     * 删除仪表板历史记录
     * @param query
     * @return
     */
    public Integer delete(DashboardHisQuery query) {
        return dashboardHisDao.delete(query);
    }

    /**
     *
     * @param id
     * @return
     */
    public DashboardHis get(String id) {
        return dashboardHisDao.get(id);
    }
    /**
     *
     * @param query
     * @return
     */
    public List<DashboardHis> list(DashboardHisQuery query) {
        return dashboardHisDao.list(query);
    }

}
