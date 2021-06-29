package com.denlaku.longan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.service.SysRoleService;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.SysRole;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/sys-role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	public Return<SysRole> get(String id) {
		SysRole sysRole = sysRoleService.get(id);
		return Resp.success(sysRole);
	}

}
