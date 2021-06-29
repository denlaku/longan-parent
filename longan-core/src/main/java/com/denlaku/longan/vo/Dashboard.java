package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dashboard extends TenantBase {

	private static Map<String, Object> defaultAttr =  new LinkedHashMap<>();
	
	private String title;
	private String desc;
	private String tag;
	private String theme;
	/**
	 * 终端类型
	 * 1-PC 2-Mobile
	 */
	private String terminal;
	private Map<String, Object> attr = defaultAttr;
	private Map<String, Object> style = defaultAttr;
	private List<Component> components;
}
