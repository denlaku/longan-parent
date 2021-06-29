package com.denlaku.longan.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public abstract class Base {

	private String id;
	private String createdBy;
	private Date createdAt;
}
