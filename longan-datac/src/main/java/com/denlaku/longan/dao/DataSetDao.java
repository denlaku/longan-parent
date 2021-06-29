package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.DataSet;
import com.denlaku.longan.vo.DataSetCache;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DataSetDao {

	/**
	 * 新增数据集
	 * @param dataSet 数据集
	 * @return 新增记录数
	 */
	@VarOp
	int add(DataSet dataSet);

	/**
	 * 更新数据集
	 * @param dataSet 数据集
	 * @return 更新记录数
	 */
	@VarOp
	int update(DataSet dataSet);

	/**
	 * 删除数据集
	 * @param id 数据集id
	 * @return 删除记录数
	 */
	int delete(String id);

	/**
	 * 查询数据集
	 * @param dataSet 查询参数
	 * @return 数据集列表
	 */
	List<DataSet> list(DataSet dataSet);

	/**
	 * 获取数据集id
	 * @param id 数据集id
	 * @return 数据集
	 */
	DataSet get(String id);

	/**
	 * 新增数据集缓存
	 * @param cache
	 * @return 新增记录数
	 */
	@VarOp
	int addCache(DataSetCache cache);

	/**
	 * 更新数据集缓存
	 * @param cache
	 * @return 更新记录数
	 */
	@VarOp
	int updateCache(DataSetCache cache);

	/**
	 * 删除数据集缓存
	 * @param id 数据集id
	 * @return 删除记录数
	 */
	int deleteCache(String id);

	/**
	 * 获取数据集缓存
	 * @param id 数据集id
	 * @return 数据集
	 */
	DataSetCache getCache(String id);

}
