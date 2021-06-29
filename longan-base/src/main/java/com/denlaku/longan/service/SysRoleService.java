package com.denlaku.longan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.SysRoleDao;
import com.denlaku.longan.vo.SysRole;

/**
 * @author tianx
 */
@Service
public class SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	public SysRole get(String id) {
		return sysRoleDao.get(id);
	}
	
	public List<SysRole> list(SysRole sysRole) {
		return sysRoleDao.list(sysRole);
	}
}
