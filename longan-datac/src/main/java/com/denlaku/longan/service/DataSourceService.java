package com.denlaku.longan.service;

import java.sql.Connection;
import java.util.List;

import com.denlaku.longan.BizModule;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.ExcelDataSourceProperties;
import com.denlaku.longan.dao.DataSourceDao;
import com.denlaku.longan.datac.DBType;
import com.denlaku.longan.datac.DBUtil;
import com.denlaku.longan.datac.DataSourcePool;
import com.denlaku.longan.util.Strings;
import com.denlaku.longan.vo.DataSourceBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
@Service
public class DataSourceService {

	@Autowired
	private DataSourceDao dataSourceDao;
	@Autowired
	private ExcelDataSourceProperties excelDataSourceProperties;

	@Autowired
	private StringEncryptor stringEncryptor;

	public String nextId() {
		return SnowFlakeService.nextId(BizModule.DATA_SOURCE);
	}

	public int add(DataSourceBean dataSource) {
		encryptPassword(dataSource);
		dataSource.setId(nextId());
		return dataSourceDao.add(dataSource);
	}

	public int update(DataSourceBean dataSource) {
		DataSourcePool.remove(dataSource);
		encryptPassword(dataSource);
		return dataSourceDao.update(dataSource);
	}

	private void encryptPassword(DataSourceBean dataSource) {
		String password = dataSource.getPassword();
		if (Strings.isNotEmpty(password)) {
			dataSource.setPassword(stringEncryptor.encrypt(password));
		}
	}

	public int delete(String id) {
		DataSourceBean dataSource = get(id);
		DataSourcePool.remove(dataSource);
		return dataSourceDao.delete(id);
	}

	public boolean test(DataSourceBean dataSource, String flag) {
		if ("1".equals(flag)) {
			String id = dataSource.getId();
			dataSource = this.get(id);
			if (dataSource != null) {
				String password = getPassword(id);
				dataSource.setPassword(password);
			} else {
				log.warn("can'n get data source by id {}", id);
			}
		}
		if (dataSource != null) {
			String driverClassName = dataSource.getDriverClassName();
			String username = dataSource.getUsername();
			String password = dataSource.getPassword();
			String url = dataSource.getUrl();
			Connection connection = DBUtil.getConnection(url, username, password, driverClassName);
			if (connection != null) {
				return true;
			}
		}
		return false;
	}

	public List<DataSourceBean> list(DataSourceBean dataSource) {
		return dataSourceDao.list(dataSource);
	}

	public DataSourceBean get(String id) {
		return dataSourceDao.get(id);
	}

	public String getPassword(String id) {
		String password = dataSourceDao.getPassword(id);
		return stringEncryptor.decrypt(password);
	}
	
	public DataSourceBean getExcelDataSource() {
		DataSourceBean dataSource = new DataSourceBean();
		dataSource.setId("0");
		DBType type = excelDataSourceProperties.getType();
		dataSource.setType(type != null ? type.getCode(): "1");
		dataSource.setUrl(excelDataSourceProperties.getUrl());
		dataSource.setDriverClassName(excelDataSourceProperties.getDriverClassName());
		dataSource.setUsername(excelDataSourceProperties.getUsername());
		dataSource.setPassword(excelDataSourceProperties.getPassword());
		dataSource.setVersion(excelDataSourceProperties.getVersion());
		return dataSource;
	}

}
