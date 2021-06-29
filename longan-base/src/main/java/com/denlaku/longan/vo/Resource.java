package com.denlaku.longan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends Base {

	private String name;
	private String path;
	private String contentType;
	private long size;
	private String status;

}
