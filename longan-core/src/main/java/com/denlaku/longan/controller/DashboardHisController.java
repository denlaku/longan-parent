package com.denlaku.longan.controller;

import com.denlaku.longan.qo.DashboardHisQuery;
import com.denlaku.longan.service.DashboardHisService;
import com.denlaku.longan.service.DashboardService;
import com.denlaku.longan.util.Objects;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.Dashboard;
import com.denlaku.longan.vo.DashboardHis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/dashboard-his")
@Api(tags="仪表板历史记录")
public class DashboardHisController {
    @Autowired
    private DashboardHisService dashboardHisService;

    @Autowired
    private DashboardService dashboardService;

    /**
     * 删除仪表板历史记录
     * @param query
     * @return
     */
    @PostMapping("/delete")
    public Return<Integer> delete(DashboardHisQuery query) {
        Integer delete = dashboardHisService.delete(query);
        return Resp.success(delete);
    }

    /**
     * 删除仪表板历史记录
     * @param query
     * @return
     */
    @PostMapping("/recovery")
    public Return<Void> recovery(DashboardHisQuery query) {
        DashboardHis dashboardHis = dashboardHisService.get(query.getId());
        Dashboard dashboard = dashboardHis.getDashboard();
        Objects.ifPresent(dashboard, dashboardService::update);
        return Resp.success();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public Return<Dashboard> get(String id) {
        DashboardHis dashboardHis = dashboardHisService.get(id);
        return Resp.success(Objects.map(dashboardHis, DashboardHis::getDashboard));
    }
    /**
     *
     * @param query
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("查询仪表板历史记录")
    public Return<List<DashboardHis>> list(@RequestBody DashboardHisQuery query) {
        List<DashboardHis> list = dashboardHisService.list(query);
        return Resp.success(list);
    }
}
