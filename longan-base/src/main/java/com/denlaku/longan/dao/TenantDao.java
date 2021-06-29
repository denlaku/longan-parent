package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Tenant;
import org.springframework.stereotype.Repository;

/**
 * 租户DAO
 * @author tianx
 */
@Repository
public interface TenantDao {

	/**
	 * 查询租户
	 * @param tenant 查询参数
	 * @return 租户列表
	 */
	List<Tenant> list(Tenant tenant);

	/**
	 * 根据id获取租户
	 * @param id 租户id
	 * @return 租户
	 */
	Tenant get(String id);

	/**
	 * 新增租户
	 * @param tenant 租户
	 * @return 新增记录数
	 */
	@VarOp
	int add(Tenant tenant);

	/**
	 * 更新租户
	 * @param tenant 租户
	 * @return 更新记录数
	 */
	@VarOp
	int update(Tenant tenant);

	/**
	 * 删除租户
	 * @param id 租户id
	 * @return 删除记录数
	 */
	int delete(String id);
}
