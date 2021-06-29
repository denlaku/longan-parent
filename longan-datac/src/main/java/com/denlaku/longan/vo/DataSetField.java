package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSetField extends TenantBase {
	private String dataSetId;
	private String name;
	private String alias;
	/** 1-维度字段 2-度量字段 */
	private String type;
	/** 数据类型: string/date/number */
	private String dataType;
	private String jdbcType;
	/** 格式 */
	private String format;
	/** 小数位 */
	private String scale;
	private List<PowerMatch> powerMatchs;
}
