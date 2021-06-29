package com.denlaku.longan.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class VarBase extends Base {

	private String updatedBy;
	private Date updatedAt;
}
