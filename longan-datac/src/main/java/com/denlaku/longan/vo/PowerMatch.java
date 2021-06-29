package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PowerMatch extends TenantBase {

	private String dataSetId;
	/** 字段或占位符ID */
	private String ofId;
	/** 0-字段 1-位符 */
	private String ofType;
	/** 数据权限空间ID */
	private String spaceId;
	/**  数据权限类型: 0-未选中 1-选中 */
	private String type;
	/** 数据权限编码: 0-未选中 1-选中 */
	private int code;
	/** 数据权限名称: 0-未选中 1-选中 */
	private int name;
	/** 数据权限预留字段: 0-未选中 1-选中 */
	private int reserve;
}
