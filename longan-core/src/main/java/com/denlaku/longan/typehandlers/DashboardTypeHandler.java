package com.denlaku.longan.typehandlers;

import com.alibaba.fastjson.JSON;
import com.denlaku.longan.vo.Dashboard;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tianx
 */
public class DashboardTypeHandler extends BaseTypeHandler<Dashboard> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Dashboard dashboard, JdbcType jdbcType)
			throws SQLException {
		if (dashboard != null) {
			ps.setObject(i, JSON.toJSONString(dashboard));
		}
	}

	@Override
	public Dashboard getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String json = rs.getString(columnName);
		return parseObject(json);
	}

	@Override
	public Dashboard getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String json = rs.getString(columnIndex);
		return parseObject(json);
	}

	@Override
	public Dashboard getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String json = cs.getString(columnIndex);
		return parseObject(json);
	}

	private Dashboard parseObject(String json) {
		if (json != null && !json.isEmpty()) {
			return JSON.parseObject(json, Dashboard.class);
		}
		return null;
	}

}
