package com.denlaku.longan.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataPowerNode extends TenantBase {

	private String spaceId;
	@ExcelProperty("类型")
	private String type;
	@ExcelProperty("编码")
	private String code;
	@ExcelProperty("名称")
	private String name;
	@ExcelProperty("预留")
	private String reserve;

}
