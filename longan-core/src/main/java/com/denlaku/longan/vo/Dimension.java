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
public class Dimension extends TenantBase {
	private static Map<String, Object> defaultAttr =  new HashMap<>();
	private String componentId;
	private String name;
	private Map<String, Object> attr = defaultAttr;
	private Order order;
}
