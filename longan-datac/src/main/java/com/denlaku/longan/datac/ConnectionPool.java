package com.denlaku.longan.datac;

import java.sql.Connection;
import java.sql.SQLException;

import com.denlaku.longan.vo.DataSourceBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
public class ConnectionPool {
	public static Connection get(DataSourceBean dataSource) throws SQLException {
		return DataSourcePool.getDataSource(dataSource).getConnection();
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	private ConnectionPool() {
	}
}
