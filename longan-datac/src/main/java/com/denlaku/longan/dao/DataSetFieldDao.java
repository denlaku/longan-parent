package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.vo.DataSetField;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DataSetFieldDao {

	/**
	 * 新增数据集字段
	 * @param field 数据集字段
	 * @return 新增记录数
	 */
	int add(DataSetField field);

	/**
	 * 删除数据集字段
	 * @param dataSetId 数据集id
	 * @return 删除记录数
	 */
	int delete(String dataSetId);

	/**
	 * 获取数据集字段
	 * @param dataSetId 数据集id
	 * @return 数据集字段列表
	 */
	List<DataSetField> list(String dataSetId);
}
