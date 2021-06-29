package com.denlaku.longan.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantBase extends VarBase {
	
	@JsonIgnore
	private String tenantId;
}
