package com.denlaku.longan.controller;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.qo.RefreshQuery;
import com.denlaku.longan.service.DashboardService;
import com.denlaku.longan.service.RefreshService;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.util.Strings;
import com.denlaku.longan.vo.BizTag;
import com.denlaku.longan.vo.ComponentData;
import com.denlaku.longan.vo.Dashboard;
import com.denlaku.longan.vo.PageBy;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/dashboard-config")
public class DashboardConfigController {

	@Autowired
	private DashboardService dashboardService;
	@Autowired
	private RefreshService refreshService;

	@PostMapping("/next")
	public Return<String> next() {
		String nextId = dashboardService.nextId();
		return Resp.success(nextId);
	}

	@PostMapping("/save")
	public Return<String> save(@RequestBody Dashboard dashboard) {
		String id = dashboard.getId();
		boolean exists = false;
		if (Strings.isEmpty(id)) {
			id = dashboardService.nextId();
			dashboard.setId(id);
		} else {
			exists = dashboardService.exists(id);
		}
		if (exists) {
			dashboardService.update(dashboard);
		} else {
			dashboardService.add(dashboard);
		}
		dashboardService.buildIndex(dashboard);
		return Resp.success(id);
	}

	@PostMapping("/copy")
	public Return<String> copy(@RequestBody Dashboard dashboard) {
		dashboardService.add(dashboard);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<String> delete(String id) {
		dashboardService.delete(id);
		return Resp.success();
	}

	@GetMapping("/get")
	public Return<Dashboard> get(@RequestParam("id") String id) {
		Dashboard dashboard = dashboardService.get(id);
		return Resp.success(dashboard);
	}

	@VarBy
	@PostMapping("/page-list")
	public Return<List<Dashboard>> pageList(@RequestBody Dashboard dashboard, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<Dashboard> list = dashboardService.list(dashboard);
		return Resp.success(list);
	}
	
	@RequestMapping("/refresh")
	public Return<ComponentData> refresh(@RequestBody RefreshQuery query) {
		query.setDebug(true);
		ComponentData refresh = refreshService.refresh(query);
		return Resp.success(refresh);
	}
	
	@RequestMapping("/refreshes")
	public Return<List<ComponentData>> refreshes(@RequestBody List<RefreshQuery> queries) {
		List<ComponentData> refresh = refreshService.refreshes(queries, true);
		return Resp.success(refresh);
	}

	@RequestMapping("/add-tag")
	public int addTag(BizTag tag) {
		return dashboardService.addTag(tag);
	}

	@RequestMapping("/delete-tag")
	public int deleteTag(BizTagQuery query) {
		return dashboardService.deleteTag(query);
	}

	@RequestMapping("/list-tag")
	public List<BizTag> listTag(BizTagQuery query) {
		return dashboardService.listTag(query);
	}
}
