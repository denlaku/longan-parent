package com.denlaku.longan.datac;

import java.util.HashMap;
import java.util.Map;

public enum JdbcType {

	// ------ string ------
	CHAR("CHAR", DataType.string), //
	NCHAR("NCHAR", DataType.string), //
	VARCHAR("VARCHAR", DataType.string), //
	VARCHAR2("VARCHAR2", DataType.string), //
	NVARCHAR2("NVARCHAR2", DataType.string), //
	TINYTEXT("TINYTEXT", DataType.string), //
	TEXT("TEXT", DataType.string), //
	MEDIUMTEXT("MEDIUMTEXT", DataType.string), //
	LONGTEXT("LONGTEXT", DataType.string), //
	// ------ number ------
	TINYINT("TINYINT", DataType.number), //
	TINYINT_UNSIGNED("TINYINT UNSIGNED", DataType.number), //
	SMALLINT("SMALLINT", DataType.number), //
	SMALLINT_UNSIGNED("SMALLINT UNSIGNED", DataType.number), //
	MEDIUMINT("MEDIUMINT", DataType.number), //
	MEDIUMINT_UNSIGNED("MEDIUMINT UNSIGNED", DataType.number), //
	INT("INT", DataType.number), //
	INT_UNSIGNED("INT UNSIGNED", DataType.number), //
	INTEGER("INTEGER", DataType.number), //
	INTEGER_UNSIGNED("INTEGER UNSIGNED", DataType.number), //
	BIGINT("BIGINT", DataType.number), //
	BIGINT_UNSIGNED("BIGINT UNSIGNED", DataType.number), //
	FLOAT("FLOAT", DataType.number), //
	DOUBLE("DOUBLE", DataType.number), //
	DECIMAL("DECIMAL", DataType.number), //
	NUMBER("NUMBER", DataType.number), //
	LONG("LONG", DataType.number), //
	// ------ date ------
	DATE("DATE", DataType.date), //
	DATETIME("DATETIME", DataType.date), //
	YEAR("YEAR", DataType.date), //
	TIME("TIME", DataType.date), //
	TIMESTAMP("TIMESTAMP", DataType.date), //
	DATE1111("DATE", DataType.date);

	private static Map<String, String> map = new HashMap<>();

	static {
		JdbcType[] values = JdbcType.values();
		for (JdbcType value : values) {
			map.put(value.getName(), value.getType());
		}
	}

	public static String getDataType(String jdbcType) {
		return map.get(jdbcType);
	}

	private String name;
	private String type;

	JdbcType(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public boolean eq(String name) {
		return this.getName().equals(name);
	}

}
