package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends VarBase {

	private String account;
	private String name;
	private String nickname;
	private String email;
	private String phone;
	/**
	 * 用户状态
	 * 1-正常 2-禁用
	 */
	private String status;

}
