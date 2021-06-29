package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.vo.DataSourceBean;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DataSourceDao {

	/**
	 * 新增数据源
	 * @param dataSource 数据源
	 * @return 新增记录数
	 */
	int add(DataSourceBean dataSource);

	/**
	 * 更新数据源
	 * @param dataSource 数据源
	 * @return 更新记录数
	 */
	int update(DataSourceBean dataSource);

	/**
	 * 删除数据源
	 * @param id 数据源id
	 * @return 删除记录数
	 */
	int delete(String id);

	/**
	 * 查询数据源
	 * @param dataSource 查询参数
	 * @return 数据源列表
	 */
	List<DataSourceBean> list(DataSourceBean dataSource);

	/**
	 * 获取数据源
	 * @param id 数据源id
	 * @return 数据源
	 */
	DataSourceBean get(String id);

	/**
	 * 获取数据源密码
	 * @param id 数据源id
	 * @return
	 */
	String getPassword(String id);
}
