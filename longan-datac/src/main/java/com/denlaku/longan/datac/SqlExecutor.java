package com.denlaku.longan.datac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.denlaku.longan.Column;
import com.denlaku.longan.util.ExceptionUtil;

/**
 * @author tianx
 */
public class SqlExecutor {

	public static SqlReturn select(Connection conn, String sql, List<Object> params) {
		SqlReturn sr = new SqlReturn();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			setParams(params, pst);
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			List<String> columnNames = new ArrayList<>();
			for (int c = 1; c <= count; c++) {
				String columnName = metaData.getColumnLabel(c);
				if (columnName == null) {
					columnName = metaData.getColumnName(c);
				}
				columnNames.add(columnName);
			}
			List<Map<String, Object>> data = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();
				for (String columnName : columnNames) {
					Object value = rs.getObject(columnName);
					row.put(columnName, value);
				}
				data.add(row);
			}
			sr.setRows(data);
		} catch (Exception e) {
			sr.setThrowable(ExceptionUtil.getRoot(e));
			sr.setStatus(true);
		}
		sr.setEnd(System.currentTimeMillis());
		sr.setSql(getSql(pst));
		sr.setParams(params);
		return sr;
	}

	private static void setParams(List<Object> params, PreparedStatement pst) throws SQLException {
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pst.setObject(i + 1, params.get(i));
			}
		}
	}

	private static String getSql(PreparedStatement pst) {
		if (pst != null) {
			String string = pst.toString();
			int index = string.indexOf(":");
			return string.substring(index + 1).trim();
		}
		return null;
	}

	public static List<Column> getMeta(Connection conn, String sql, List<Object> params) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(sql);
		setParams(params, pst);
		ResultSet rs = pst.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int count = metaData.getColumnCount();
		List<Column> columns = new ArrayList<>();
		for (int c = 1; c <= count; c++) {
			Column column = new Column();
			String columnName = metaData.getColumnName(c);
			String columnLabel = metaData.getColumnLabel(c);
			column.setName(columnName);
			column.setLabel(columnLabel == null ? columnName : columnLabel);
			column.setType(metaData.getColumnType(c));
			column.setTypeName(metaData.getTableName(c));
			column.setScale(metaData.getScale(c));
			columns.add(column);
		}
		return columns;
	}
	
	public static void create(Connection conn, String sql) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.execute();
	}
}
