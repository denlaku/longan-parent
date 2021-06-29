package com.denlaku.longan.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.denlaku.longan.vo.Component;

/**
 * @author tianx
 */
public class ComponentTypeHandler extends BaseTypeHandler<Component> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Component component, JdbcType jdbcType)
			throws SQLException {
		if (component != null) {
			ps.setObject(i, JSON.toJSONString(component));
		}
	}

	@Override
	public Component getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String json = rs.getString(columnName);
		return parseObject(json);
	}

	@Override
	public Component getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String json = rs.getString(columnIndex);
		return parseObject(json);
	}

	@Override
	public Component getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String json = cs.getString(columnIndex);
		return parseObject(json);
	}

	private Component parseObject(String json) {
		if (json != null && !json.isEmpty()) {
			return JSON.parseObject(json, Component.class);
		}
		return null;
	}

}
