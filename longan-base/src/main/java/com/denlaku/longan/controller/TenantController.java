package com.denlaku.longan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.service.TenantService;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.Tenant;
import com.github.pagehelper.PageHelper;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/tenant")
public class TenantController {

	@Autowired
	private TenantService tenantService;

	@PostMapping("/page-list")
	@VarBy
	public Return<List<Tenant>> pageList(Tenant tenant, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<Tenant> list = tenantService.list(tenant);
		return Resp.success(list);
	}

	@RequestMapping("/get")
	public Return<Tenant> get(String id) {
		Tenant tenant = tenantService.get(id);
		return Resp.success(tenant);
	}

	@PostMapping("/add")
	public Return<Void> add(Tenant tenant) {
		tenantService.add(tenant);
		return Resp.success();
	}

	@PostMapping("/update")
	public Return<Void> update(Tenant tenant) {
		tenantService.update(tenant);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> delete(String id) {
		tenantService.delete(id);
		return Resp.success();
	}

}
