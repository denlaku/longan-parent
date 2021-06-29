package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.qo.DataPowerSpaceQuery;
import com.denlaku.longan.vo.DataPowerSpace;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DataPowerSpaceDao {

	/**
	 * 新增权限空间
	 * @param dataPowerSpace 权限空间
	 * @return 新增记录数
	 */
	@VarOp
	int add(DataPowerSpace dataPowerSpace);

	/**
	 * 更新权限空间
	 * @param dataPowerSpace 权限空间
	 * @return 更新记录数
	 */
	@VarOp
	int update(DataPowerSpace dataPowerSpace);

	/**
	 * 删除权限空间
	 * @param query 参数
	 * @return 删除记录数
	 */
	int delete(DataPowerSpaceQuery query);

	/**
	 * 获取权限空间
	 * @param query 查询参数
	 * @return 权限空间
	 */
	DataPowerSpace get(DataPowerSpaceQuery query);

	/**
	 * 查询权限空间
	 * @param query 查询参数
	 * @return 权限空间列表
	 */
	List<DataPowerSpace> list(DataPowerSpaceQuery query);
}
