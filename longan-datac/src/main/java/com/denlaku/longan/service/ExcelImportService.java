package com.denlaku.longan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.AppException;
import com.denlaku.longan.datac.ConnectionPool;
import com.denlaku.longan.datac.JdbcType;
import com.denlaku.longan.datac.SqlExecutor;
import com.denlaku.longan.util.DateUtil;
import com.denlaku.longan.vo.DataSourceBean;
import com.denlaku.longan.vo.ExcelCell;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Service
@Slf4j
public class ExcelImportService {
	@Autowired
	private DataSourceService dataSourceService;

	public String create(List<String> heads, Map<String, String> typeMap) {
		try {
			String tableName = String.format("t_%s", DateUtil.format("yyyy_MM_dd_HHmmss"));
			String sql = createSql(heads, typeMap, tableName);
			DataSourceBean dataSource = dataSourceService.getExcelDataSource();
			Connection connection = ConnectionPool.get(dataSource);
			SqlExecutor.create(connection, sql);
			return tableName;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException("excel导入时建表失败", e);
		}
	}

	public void intsert(List<String> heads, Map<String, String> typeMap, String tableName, List<List<ExcelCell>> rows) {
		try {
			String sql = insertSql(heads, tableName);
			DataSourceBean dataSource = dataSourceService.getExcelDataSource();
			Connection connection = ConnectionPool.get(dataSource);
			PreparedStatement pst = connection.prepareStatement(sql);
			int count = 0;
			for (List<ExcelCell> row: rows) {
				int size = row.size();
				for (int i = 0; i < size; i++) {
					ExcelCell cell = row.get(i);
					String head = cell.getHead();
					String type = typeMap.get(head);
					pst.setObject(i + 1, JdbcType.VARCHAR.eq(type) ? cell.getText(): cell.getValue());
				}
				pst.addBatch();
				count++;
				if (count % 5000 == 0) {
					pst.executeBatch();
				}
			}
			if (count % 5000 != 0) {
				pst.executeBatch();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException("excel导入数据失败", e);
		}
		
		
	}

	private String insertSql(List<String> heads, String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append(tableName);
		sb.append("(");
		int size = heads.size();
		int i = size;
		for (String head : heads) {
			sb.append(head);
			if (i > 1) {
				sb.append(",");
			}
			i--;
		}
		sb.append(") values (");
		i = size;
		for (; i > 0; i--) {
			sb.append("?");
			if (size > 1) {
				sb.append(",");
			}
			size--;
		}
		sb.append(")");
		return sb.toString();
	}

	private String createSql(List<String> heads, Map<String, String> typeMap, String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ");
		sb.append(tableName);
		sb.append("(");
		sb.append("`_eid_` bigint not null auto_increment,");
		for (String head : heads) {
			String type = typeMap.get(head);
			if (JdbcType.VARCHAR.name().equals(type)) {
				sb.append(String.format("`%s` varchar(520)", head));
			} else if (JdbcType.DECIMAL.name().equals(type)) {
				sb.append(String.format("`%s` decimal(30,6)", head));
			} else {
				sb.append(String.format("`%s` %s", head, type));
			}
			sb.append(",");
		}
		sb.append("primary key (`_eid_`)");
		sb.append(")");
		return sb.toString();
	}
}
