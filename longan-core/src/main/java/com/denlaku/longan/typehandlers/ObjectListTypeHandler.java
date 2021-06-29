package com.denlaku.longan.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;

/**
 * @author tianx
 */
public class ObjectListTypeHandler extends BaseTypeHandler<List<Object>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<Object> list, JdbcType jdbcType)
			throws SQLException {
		if (list != null) {
			ps.setObject(i, JSON.toJSONString(list));
		}
	}

	@Override
	public List<Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String json = rs.getString(columnName);
		return parseObject(json);
	}

	@Override
	public List<Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String json = rs.getString(columnIndex);
		return parseObject(json);
	}

	@Override
	public List<Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String json = cs.getString(columnIndex);
		return parseObject(json);
	}

	private List<Object> parseObject(String json) {
		if (json != null && !json.isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Object> list = JSON.parseObject(json, List.class);
			return list;
		}
		return new ArrayList<>();
	}

}
