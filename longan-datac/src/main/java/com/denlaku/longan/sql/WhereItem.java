package com.denlaku.longan.sql;

import java.util.List;

/**
 * @author tianx
 */
public class WhereItem {

	private String name;
	private String operator;
	private List<Object> values;
	private boolean ifNull;
	private boolean ifNotNull;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public boolean isIfNull() {
		return ifNull;
	}

	public void setIfNull(boolean ifNull) {
		this.ifNull = ifNull;
	}

	public boolean isIfNotNull() {
		return ifNotNull;
	}

	public void setIfNotNull(boolean ifNotNull) {
		this.ifNotNull = ifNotNull;
	}

}
