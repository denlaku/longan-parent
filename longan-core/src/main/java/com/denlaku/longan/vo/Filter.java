package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Filter extends TenantBase {

	private String componentId;
	private String name;
	private String aggregator;
	private String operator;
	private List<Object> values;
}
