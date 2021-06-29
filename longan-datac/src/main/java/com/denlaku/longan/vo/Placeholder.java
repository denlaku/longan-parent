package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Placeholder extends TenantBase {

	private String dataSetId;
	private String name;
	private String type;
	private String format;
	private String delimiter;
	private String defValue;
	private String value;
	private List<PowerMatch> powerMatchs;
}
