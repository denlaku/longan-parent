package com.denlaku.longan;

/**
 * 操作系统
 *
 * @author tianx
 */

public enum Platform {
	/**
	 * linux
	 */
	LINUX("linux"),
	/**
	 * unix
	 */
	UNIX("unix"),
	/**
	 * windows
	 */
	WINDOWS("windows"),
	/**
	 * mac
	 */
	MAC("mac"),
	/**
	 * unknown
	 */
	UNKNOWN("unknown");

	/**
	 * name
	 */
	private String name;

	Platform(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
