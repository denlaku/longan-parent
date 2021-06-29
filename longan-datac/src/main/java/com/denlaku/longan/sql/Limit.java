package com.denlaku.longan.sql;

/**
 * @author tianx
 */
public class Limit {

	private int start;
	private int end;
	private int size = 10;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
