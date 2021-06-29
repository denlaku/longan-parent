package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Tenant;
import com.denlaku.longan.vo.TenantMember;
import org.springframework.stereotype.Repository;

/**
 * 租户成员DAO
 * @author tianx
 */
@Repository
public interface TenantMemberDao {

	/**
	 * 查询租户成员
	 * @param tenant 查询参数
	 * @return
	 */
	List<TenantMember> list(Tenant tenant);

	/**
	 * 根据id获取租户成员
	 * @param id 成员id
	 * @return
	 */
	TenantMember get(String id);

	/**
	 * 添加租户成员
	 * @param tenantMember 租户成员
	 * @return 新增记录数
	 */
	@VarOp
	int add(TenantMember tenantMember);

	/**
	 * 删除户成员
	 * @param id 租户成员id
	 * @return 删除记录数
	 */
	int delete(String id);
}
