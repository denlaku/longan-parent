package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomPage extends TenantBase {

	/**
	 * 自定义页面名称
	 */
	private String name;
	/**
	 * 自定义页面状态:
	 * 0-草稿 1-发布
	 */
	private int status;
	/**
	 * 自定义页面备注信息
	 */
	private String remark;
}
