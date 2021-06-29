package com.denlaku.longan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 基础信息
 * @author tianx
 */
@ConfigurationProperties("longan")
public class ConfigProperties {

	private String dir;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
