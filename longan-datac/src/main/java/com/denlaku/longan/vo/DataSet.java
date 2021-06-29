package com.denlaku.longan.vo;

import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSet extends TenantBase {
	private static final long DEFAULT_QUERY_TIMEOUT = MINUTES.toMillis(3);
	private String name;
	private String dataSourceId;
	/** 1-sql 2-excel */
	private String type;
	private String command;
	private List<DataSetField> fields;
	private List<Placeholder> placeholders;
	/** 查询超时时 */
	private Long queryTimeout = DEFAULT_QUERY_TIMEOUT;
	/** 数据缓存时长 */
	private long cacheTimeout;

}
