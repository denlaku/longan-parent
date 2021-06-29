package com.denlaku.longan.datac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
public class DBUtil {

	public static Connection getConnection(String url, String username, String password, String driverClassName) {
		try {
			Class.forName(driverClassName);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static void colseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	private DBUtil() {
	}
}
