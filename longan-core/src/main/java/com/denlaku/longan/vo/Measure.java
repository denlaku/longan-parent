package com.denlaku.longan.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Measure extends TenantBase {
	private static Map<String, Object> defaultAttr =  new HashMap<>();
	private String componentId;
	private String name;
	private String aggregator;
	private Map<String, Object> attr = defaultAttr;
	private Order order;
}
