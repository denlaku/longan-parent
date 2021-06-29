package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Tenant extends VarBase {

	private String name;
	/**
	 * 租户状态
	 * 1-正常 2-禁用
	 */
	private String status;

}
