package com.denlaku.longan.controller;

import java.util.List;
import java.util.Map;

import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.denlaku.longan.qo.RefreshQuery;
import com.denlaku.longan.service.RefreshService;
import com.denlaku.longan.service.DashboardService;
import com.denlaku.longan.vo.ComponentData;
import com.denlaku.longan.vo.Dashboard;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/dashboard-view")
@Api(tags = "仪表板视图")
public class DashboardViewController {

	@Autowired
	private DashboardService dashboardService;
	@Autowired
	private RefreshService refreshService;

	@ApiOperation(value = "获取单个仪表板")
	@GetMapping("/get")
	public Return<Dashboard> get(@RequestParam("id") String id) {
		Dashboard dashboard = dashboardService.get(id);
		return Resp.success(dashboard);
	}

	@ApiOperation(value = "刷新仪表板")
	@PostMapping("/refreshes")
	public Return<List<ComponentData>> refreshes(@RequestBody List<RefreshQuery> queries,//
			@RequestParam(defaultValue = "false") boolean debug) {
		List<ComponentData> refresh = refreshService.refreshes(queries, debug);
		return Resp.success(refresh);
	}

	@ApiOperation(value = "搜索")
	@GetMapping("/search")
	public Return<List<Map<String, String>>> search(@RequestParam("keyword") String keyword) {
		if (Strings.isEmpty(keyword)) {
			return Resp.success(Lists.empty());
		}
		List<Map<String, String>> result = dashboardService.search(keyword);
		return Resp.success(result);
	}
	
}
