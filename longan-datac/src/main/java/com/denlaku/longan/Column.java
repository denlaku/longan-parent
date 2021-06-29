package com.denlaku.longan;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public class Column {
	private String name;
	private String label;
	private int type;
	private String typeName;
	private int scale;
}
