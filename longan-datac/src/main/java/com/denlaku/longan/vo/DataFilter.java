package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataFilter extends TenantBase {
	private Long belong;
	private String name;
	/** = != < <= < <= like */
	private String operator;
	private List<Object> values;
}
