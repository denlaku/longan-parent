package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.vo.Placeholder;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface PlaceholderDao {

	/**
	 * 新增占位符
	 * @param placeholder 占位符
	 * @return 新增记录数
	 */
	int add(Placeholder placeholder);

	/**
	 * 删除占位符
	 * @param dataSetId 数据集id
	 * @return 删除记录数
	 */
	int delete(String dataSetId);

	/**
	 * 查询占位符
	 * @param dataSetId 数据集id
	 * @return 占位符列表
	 */
	List<Placeholder> list(String dataSetId);
}
