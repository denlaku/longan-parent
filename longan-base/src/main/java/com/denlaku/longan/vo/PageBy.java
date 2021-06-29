package com.denlaku.longan.vo;

/**
 * @author tianx
 */
public class PageBy {

	private int page;
	private int size = 15;
	private int start;
	private int end;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		if (this.page <= 0) {
			this.page = 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

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
}
