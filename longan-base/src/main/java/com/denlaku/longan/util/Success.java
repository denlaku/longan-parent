package com.denlaku.longan.util;

import com.denlaku.longan.vo.User;

import java.util.Map;

class Success<T> implements Return<T> {

	private boolean status = true;
	private T data;
	private Object ext;
	private Map<String, User> umap;
	private String code;
	private String msg;

	public Success(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
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
