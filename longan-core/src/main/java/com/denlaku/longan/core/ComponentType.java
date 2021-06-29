package com.denlaku.longan.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianx
 */

public enum ComponentType {
	/**
	 * 空白
	 */
	BLANK("blank", "0", "空白", 0),
	/**
	 * 折线图
	 */
	LINE("line", "0", "折线图", 1),
	/**
	 * 柱状图
	 */
	BAR("bar", "0", "柱状图", 2),
	/**
	 * 饼图
	 */
	PIE("pie", "0", "饼图", 3),
	/**
	 * 二维表
	 */
	TABLE("table", "0", "二维表", 4),
	/**
	 * 雷达图
	 */
	RADAR("radar", "0", "雷达图", 5),
	/**
	 * 树图
	 */
	TREE("tree", "0", "树图", 6),
	/**
	 * 指标板
	 */
	INDICES("indices", "0", "指标板", 7),
	/**
	 * K线图
	 */
	CANDLESTICK("candlestick", "0", "K线图", 8),
	/**
	 * 平行坐标系
	 */
	PARALLEL("parallel", "0", "平行坐标系", 9),
	/**
	 * 关系图
	 */
	GRAPH("graph", "0", "关系图", 10),
	/**
	 * 富文本
	 */
	RICHTEXT("richtext", "0", "富文本", 11),
	/**
	 * 进度条
	 */
	PROGRESS("progress", "0", "进度条", 12),
	/**
	 * 下拉选
	 */
	SELECT("select", "1", "下拉选", 1),
	/**
	 * 选择树
	 */
	TREESELECT("treeselect", "1", "选择树", 2),
	/**
	 * 输入框
	 */
	INPUT("input", "1", "输入框", 3),
	/**
	 * tab页
	 */
	TABS("tabs", "1", "tab页", 4),
	/**
	 * 未知
	 */
	TAB("tab", "1", "未知", 5),
	/**
	 * 日期
	 */
	DATE("date", "1", "日期", 6),
	/**
	 * 时间
	 */
	TIME("time", "1", "时间", 7);

	private static final Map<String, ComponentType> map = new HashMap<>();
	static {
		for (ComponentType chartType : values()) {
			map.put(chartType.getCode(), chartType);
		}
	}

	public static ComponentType get(String type) {
		ComponentType match = map.get(type);
		if (match == null) {
			match = ComponentType.BLANK;
		}
		return match;
	}

	private String code;
	private String cls;
	private String name;
	private int order;

	ComponentType(String code, String cls, String name, int order) {
		this.code = code;
		this.cls = cls;
		this.name = name;
		this.order = order;
	}

	public String getCode() {
		return code;
	}

	public String getCls() {
		return cls;
	}

	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}

	public boolean eq(String type) {
		return this.getCode().equals(type);
	}

}
