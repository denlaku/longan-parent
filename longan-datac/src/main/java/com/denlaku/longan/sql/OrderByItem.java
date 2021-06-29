package com.denlaku.longan.sql;

import java.util.List;

/**
 * @author tianx
 */
public class OrderByItem {

	private String name;
	private String order;
	private boolean nullFirst;
	private List<Object> values;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public boolean isNullFirst() {
		return nullFirst;
	}

	public void setNullFirst(boolean nullFirst) {
		this.nullFirst = nullFirst;
	}

}
