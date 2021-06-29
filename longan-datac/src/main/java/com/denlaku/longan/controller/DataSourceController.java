package com.denlaku.longan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.service.DataSourceService;
import com.denlaku.longan.vo.DataSourceBean;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/data-source")
public class DataSourceController {

	@Autowired
	private DataSourceService dataSourceService;

	@VarOp
	@PostMapping("/add")
	public Return<Void> add(@RequestBody DataSourceBean dataSource) {
		dataSourceService.add(dataSource);
		return Resp.success();
	}

	@VarOp
	@PostMapping("/update")
	public Return<Void> update(@RequestBody DataSourceBean dataSource) {
		dataSourceService.update(dataSource);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> delete(String id) {
		dataSourceService.delete(id);
		return Resp.success();
	}

	@PostMapping("/test")
	public Return<Boolean> test(@RequestBody DataSourceBean dataSource, String flag) {
		boolean test = dataSourceService.test(dataSource, flag);
		return Resp.success(test);
	}

	@VarBy
	@GetMapping("/page-list")
	public Return<List<DataSourceBean>> pageList(DataSourceBean dataSource, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<DataSourceBean> list = dataSourceService.list(dataSource);
		return Resp.success(list);
	}

	@GetMapping("/list")
	public Return<List<DataSourceBean>> pageList(DataSourceBean dataSource) {
		List<DataSourceBean> list = dataSourceService.list(dataSource);
		return Resp.success(list);
	}

	@GetMapping("/get")
	public Return<DataSourceBean> get(@RequestParam("id") String id) {
		DataSourceBean dataSource = dataSourceService.get(id);
		return Resp.success(dataSource);
	}

}
