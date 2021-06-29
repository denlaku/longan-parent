package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Resource;
import org.springframework.stereotype.Repository;

/**
 * 资源管理DAO
 * @author tianx
 */
@Repository
public interface ResourceDao {

	/**
	 * 新增资源信息
	 * @param resource 资源信息
	 * @return 新增记录数
	 */
	@VarOp
	int add(Resource resource);

	/**
	 * 删除资源信息
	 * @param id 资源id
	 * @return 删除记录数
	 */
	int delete(String id);

	/**
	 * 根据id获取资源信息
	 * @param id 资源id
	 * @return 资源信息
	 */
	Resource get(String id);

	/**
	 * 根据参数查询资源信息
	 * @param resource 查询参数
	 * @return 资源信息列表
	 */
	List<Resource> list(Resource resource);
}
