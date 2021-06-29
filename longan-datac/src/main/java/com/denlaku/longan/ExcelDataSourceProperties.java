package com.denlaku.longan;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.denlaku.longan.datac.DBType;

import lombok.Data;

/**
 * @author tianx
 */
@ConfigurationProperties("excel.datasource")
@Data
@Component
public class ExcelDataSourceProperties {

	private DBType type;
	private String url;
	private String driverClassName;
	private String version;
	private String username;
	private String password;
}
