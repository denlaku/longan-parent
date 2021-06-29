package com.denlaku.longan.qo;

import com.denlaku.longan.Current;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@Data
public abstract class Query {

	private String id;
	@ApiModelProperty(hidden = true)
	private String tenantId;

	protected Query() {
		String tenantId = Current.getTenantId();
		this.setTenantId(tenantId);
	}
	
}
