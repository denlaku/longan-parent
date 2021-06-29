package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomApi extends TenantBase {

	private String name;
	private String dataSetId;
	private List<CustomApiField> fields;
	private String status;
	
}
