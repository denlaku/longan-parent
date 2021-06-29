package com.denlaku.longan.datac;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.denlaku.longan.vo.DataSourceBean;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author tianx
 */
public class DataSourcePool {

	private static final Object monitor = new Object();
	private static final Map<String, DataSource> map = new HashMap<>();

	public static DataSource getDataSource(DataSourceBean bean) {
		String key = toKey(bean);
		DataSource dataSource = map.get(key);
		if (dataSource == null) {
			RuntimeException except = null;
			synchronized (monitor) {
				try {
					dataSource = map.get(key);
					if (dataSource == null) {
						dataSource = createDataSource(bean);
						map.put(key, dataSource);
					}
				} catch (Exception e) {
					except = new RuntimeException(e);
				}
			}
			if (except != null) {
				throw except;
			}
		}

		return dataSource;
	}

	public static void remove(DataSourceBean bean) {
		String key = toKey(bean);
		map.remove(key);
	}

	private static String toKey(DataSourceBean bean) {
		StringBuilder sb = new StringBuilder(bean.getUrl());
		sb.append(bean.getUsername());
		sb.append(bean.getDriverClassName());
		String key = sb.toString();
		return key;
	}

	private static DataSource createDataSource(DataSourceBean bean) {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(bean.getDriverClassName());
		dataSource.setJdbcUrl(bean.getUrl());
		dataSource.setUsername(bean.getUsername());
		dataSource.setPassword(bean.getPassword());
		return dataSource;
	}

	private DataSourcePool() {
	}

}
