package com.denlaku.longan.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.datac.ConnectionPool;
import com.denlaku.longan.datac.SqlExecutor;
import com.denlaku.longan.datac.SqlReturn;
import com.denlaku.longan.sql.SqlCommand;
import com.denlaku.longan.sql.SqlCommandBuilder;
import com.denlaku.longan.sql.SqlInfo;
import com.denlaku.longan.vo.DataSet;
import com.denlaku.longan.vo.DataSourceBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Service
@Slf4j
public class DataFetchService {
	
	@Autowired
	private SqlCommandBuilder sqlCommandBuilder;
	@Autowired
	private DataSourceService dataSourceService;

	public SqlReturn query(SqlInfo info) {
		SqlCommand command = sqlCommandBuilder.build(info);
		DataSet dataSet = info.getDataSet();
		String dataSourceId = dataSet.getDataSourceId();
		DataSourceBean dataSource = null;
		if ("0".equals(dataSourceId)) {
			dataSource = dataSourceService.getExcelDataSource();
		} else {
			dataSource = dataSourceService.get(dataSourceId);
			if (dataSource != null) {
				String password = dataSourceService.getPassword(dataSourceId);
				dataSource.setPassword(password);
			}
		}
		String sql = command.getSql();
		List<Object> params = command.getParams();
		Connection connection = null;
		try {
			connection = ConnectionPool.get(dataSource);
			return SqlExecutor.select(connection, sql, params);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			ConnectionPool.close(connection);
		}
		return null;
	}
}
