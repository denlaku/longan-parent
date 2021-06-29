package com.denlaku.longan.util;

import java.util.Map;

import com.denlaku.longan.vo.User;
import com.github.pagehelper.Page;

class PageSuccess<T> implements Return<T> {

	private boolean status = true;
	private T data;
	private Object ext;
	private Map<String, User> umap;
	private String code;
	private String msg;
	private long total;
	private int page;
	private int pages;

	public PageSuccess(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
		if (data != null && data instanceof Page) {
			Page<?> tmp = (Page<?>) data;
			this.total = tmp.getTotal();
			this.page = tmp.getPageNum();
			this.pages = tmp.getPages();
		}
	}

	public boolean isStatus() {
		return status;
	}

	@Override
	public T getData() {
		return data;
	}

	public Return<T> setData(T data) {
		this.data = data;
		return this;
	}

	public Object getExt() {
		return ext;
	}

	@Override
    public Return<T> setExt(Object ext) {
		this.ext = ext;
		return this;
	}

	public Map<String, User> getUmap() {
		return umap;
	}

	@Override
	public Return<T> setUmap(Map<String, User> umap) {
		this.umap = umap;
		return this;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	@Override
	public Return<T> setCode(String code) {
		this.code = code;
		return this;
	}
}
