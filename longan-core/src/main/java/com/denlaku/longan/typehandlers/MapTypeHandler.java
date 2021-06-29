package com.denlaku.longan.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;

/**
 * @author tianx
 */
public class MapTypeHandler extends BaseTypeHandler<Map<String, Object>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> map, JdbcType jdbcType)
			throws SQLException {
		if (map != null) {
			ps.setObject(i, JSON.toJSONString(map));
		}
	}

	@Override
	public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String json = rs.getString(columnName);
		return parseObject(json);
	}

	@Override
	public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String json = rs.getString(columnIndex);
		return parseObject(json);
	}

	@Override
	public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String json = cs.getString(columnIndex);
		return parseObject(json);
	}

	private Map<String, Object> parseObject(String json) {
		if (json != null && !json.isEmpty()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = JSON.parseObject(json, Map.class);
			return map;
		}
		return new HashMap<>();
	}

}
