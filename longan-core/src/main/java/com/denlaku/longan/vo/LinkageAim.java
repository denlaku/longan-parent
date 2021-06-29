package com.denlaku.longan.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkageAim extends TenantBase {

	private String linkageId;
	private Short type;
	private String componentId;
	private String name;
	private List<String> placeholders;

}
