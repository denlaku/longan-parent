package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomApiField extends TenantBase {

	private String apiId;
	private String name;
	private String aggregator;
	private Order order;
	
}
