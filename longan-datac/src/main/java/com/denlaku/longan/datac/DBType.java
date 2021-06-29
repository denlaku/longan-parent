package com.denlaku.longan.datac;

/**
 * @author tianx
 */

public enum DBType {
	/**
	 * MySQL
	 */
	MySQL("1", "MySQL"),
	/**
	 * H2
	 */
	H2("3", "H2"),
	/**
	 * Oracle
	 */
	Oracle("3", "Oracle"),
	/**
	 * DB2
	 */
	DB2("4", "DB2"),
	/**
	 * SQL Server
	 */
	SQLServer("5", "SQL Server");

	private String code;
	private String name;

	DBType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
