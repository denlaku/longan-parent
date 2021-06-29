package com.denlaku.longan.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.denlaku.longan.util.Strings;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.denlaku.longan.vo.DataSet;

/**
 * @author tianx
 */
public class DataSetTypeHandler extends BaseTypeHandler<DataSet> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DataSet DataSet, JdbcType jdbcType)
			throws SQLException {
		if (DataSet != null) {
			ps.setObject(i, JSON.toJSONString(DataSet));
		}
	}

	@Override
	public DataSet getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String json = rs.getString(columnName);
		return parseObject(json);
	}

	@Override
	public DataSet getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String json = rs.getString(columnIndex);
		return parseObject(json);
	}

	@Override
	public DataSet getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String json = cs.getString(columnIndex);
		return parseObject(json);
	}
	
	private DataSet parseObject(String json) {
		if (Strings.isNotEmpty(json)) {
			return JSON.parseObject(json, DataSet.class);
		}
		return null;
	}

}
