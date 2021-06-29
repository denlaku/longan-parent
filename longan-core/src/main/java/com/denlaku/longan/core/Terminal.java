package com.denlaku.longan.core;

/**
 * @author tianx
 */

public enum Terminal {
	/**
	 * PC端
	 */
	PC("1", "PC"),
	/**
	 * 移动端
	 */
	MOBILE("1", "Mobile");

	private String code;
	private String name;

	private Terminal(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public boolean eq(String code) {
		return this.getCode().equals(code);
	}
}
