package com.denlaku.longan.vo;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public class ExcelCell {
	private int row;
	private int col;
	private String head;
	private String text;
	private String type;
	private Object value;

	public ExcelCell(int row, int col, String head, String text) {
		super();
		this.row = row;
		this.col = col;
		this.head = head;
		this.text = text;
	}

}
