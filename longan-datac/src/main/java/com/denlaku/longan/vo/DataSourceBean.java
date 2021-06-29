package com.denlaku.longan.vo;

import static java.util.concurrent.TimeUnit.MINUTES;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSourceBean extends TenantBase {

	private static final long DEFAULT_IDLE_TIMEOUT = MINUTES.toMillis(10);
	private static final long DEFAULT_MAX_LIFETIME = MINUTES.toMillis(30);
	private static final int DEFAULT_POOL_SIZE = 10;

	/** 数据源名称 */
	private String name;
	/** 数据源类型: MySQL/Oracle/DB2/SQL Server */
	private String type;
	private String url;
	private String driverClassName;
	private String host;
	private String version;
	private String port;
	private String username;
	private String password;
	/** 最大连接数 */
	private int maxPoolSize = DEFAULT_POOL_SIZE;
	/** 最小闲置连接数 */
	private int minIdle;
	/** 连接最大生命周期 */
	private long maxLifetime = DEFAULT_MAX_LIFETIME;
	/** 连接的最大闲置时间 */
	private long idleTimeout = DEFAULT_IDLE_TIMEOUT;
	private boolean readOnly;
}
