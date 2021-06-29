package com.denlaku.longan.sql;

import java.util.List;

import com.denlaku.longan.vo.DataSourceBean;

/**
 * @author tianx
 */
public class SqlCommand {

	private String sql;
	private List<Object> params;
	private DataSourceBean dataSource;

	public SqlCommand() {
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	public void setDataSource(DataSourceBean dataSource) {
		this.dataSource = dataSource;
	}

	public DataSourceBean getDataSource() {
		return dataSource;
	}

}
