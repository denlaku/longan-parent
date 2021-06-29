package com.denlaku.longan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.service.TenantMemberService;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.Tenant;
import com.denlaku.longan.vo.TenantMember;
import com.github.pagehelper.PageHelper;

/**
 * @author tianx
 */
@RequestMapping("/api/tenant-member")
public class TenantMemberController {

	@Autowired
	private TenantMemberService tenantMemberService;

	@PostMapping("/page-list")
	@VarBy
	public Return<List<TenantMember>> pageList(Tenant tenant, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<TenantMember> list = tenantMemberService.list(tenant);
		return Resp.success(list);
	}

	@RequestMapping("/get")
	public Return<TenantMember> get(String id) {
		TenantMember tenantMember = tenantMemberService.get(id);
		return Resp.success(tenantMember);
	}

	@PostMapping("/add")
	public Return<Void> add(TenantMember tenantMember) {
		tenantMemberService.add(tenantMember);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> delete(String id) {
		tenantMemberService.delete(id);
		return Resp.success();
	}

}
