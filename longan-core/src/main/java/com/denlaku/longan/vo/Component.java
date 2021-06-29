package com.denlaku.longan.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Component extends TenantBase {
	
	private static Map<String, Object> defaultAttr =  new HashMap<>();
	
	private String name;
	/** 1-控件2-图标 */
	private String type;
	private String dataSetId;
	/**所属ID*/
	private String dashboardId;
	private List<Dimension> dimensions;
	private List<Measure> measures;
	private List<Linkage> linkages;
	private List<Goingin> goingins;
	private List<Filter> filters;
	private Map<String, Object> pgrid;
	private Map<String, Object> grid;
	private Map<String, Object> style = defaultAttr;
	private Map<String, Object> attr = defaultAttr;
	private List<Component> components;
	private String parentId;
}
