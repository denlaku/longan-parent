package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.vo.SysRole;
import org.springframework.stereotype.Repository;

/**
 * 系统角色DAO
 * @author tianx
 */
@Repository
public interface SysRoleDao {

	/**
	 * 根据ID获取系统角色
	 * @param id 角色id
	 * @return 系统角色
	 */
	SysRole get(String id);

	/**
	 * 根据参数查询系统角色
	 * @param sysRole 查询参数
	 * @return 系统角色列表
	 */
	List<SysRole> list(SysRole sysRole);
}
