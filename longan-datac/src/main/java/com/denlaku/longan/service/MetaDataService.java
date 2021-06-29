package com.denlaku.longan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.denlaku.longan.datac.ConnectionPool;
import com.denlaku.longan.datac.JdbcType;
import com.denlaku.longan.vo.DataSourceBean;
import com.denlaku.longan.vo.MetaData;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
@Service
public class MetaDataService {

	public List<MetaData> list(DataSourceBean dataSource, String sql) {
		List<MetaData> list = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.get(dataSource);
			PreparedStatement pst = connection.prepareStatement(sql + " where 1=2");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnLabel = metaData.getColumnLabel(i);
				if (columnLabel == null || columnLabel.isEmpty()) {
					columnLabel = metaData.getColumnName(i);
				}
				String typeName = metaData.getColumnTypeName(i);
				String dataType = JdbcType.getDataType(typeName);
				MetaData md = new MetaData();
				md.setName(columnLabel);
				md.setJdbcType(typeName);
				md.setDataType(dataType);
				list.add(md);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			ConnectionPool.close(connection);
		}

		return list;
	}

}
