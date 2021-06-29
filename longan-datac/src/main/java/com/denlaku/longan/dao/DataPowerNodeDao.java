package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.qo.DataPowerNodeQuery;
import com.denlaku.longan.vo.DataPowerNode;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DataPowerNodeDao {

	/**
	 * 批量新增数据权限节点
	 * @param dataPowerNodes 数据权限节点
	 * @return 新增记录数
	 */
	@VarOp
	int batchAdd(List<DataPowerNode> dataPowerNodes);

	/**
	 * 更新据权限节点
	 * @param dataPowerNode 据权限节点
	 * @return 修改记录数
	 */
	@VarOp
	int update(DataPowerNode dataPowerNode);

	/**
	 * 删除据权限节点
	 * @param query 参数
	 * @return 删除记录数
	 */
	int delete(DataPowerNodeQuery query);

	/**
	 * 获取据权限节点
	 * @param query 查询参数
	 * @return 据权限节点
	 */
	DataPowerNode get(DataPowerNodeQuery query);

	/**
	 * 查询据权限节点
	 * @param query 查询参数
	 * @return 据权限节点列表
	 */
	List<DataPowerNode> list(DataPowerNodeQuery query);

}
