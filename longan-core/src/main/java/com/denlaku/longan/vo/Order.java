package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends TenantBase {

	public static final String DESC = "desc";
	public static final String ASC = "asc";

	/** 组件ID */
	private Long componentId;
	/** 维度/度量名称 */
	private String name;
	/** desc/asc */
	private String policy;
	private boolean nullFirst;
	/** 用于排序的值 */
	private List<Object> values;
}
