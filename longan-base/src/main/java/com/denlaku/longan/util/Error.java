package com.denlaku.longan.util;

class Error<T> implements Return<T> {
	private boolean status;
	private Object data;
	private Object ext;
	private String code;
	private String msg;

	public Error(String msg, Object data) {
		super();
		this.msg = msg;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	@Override
	public Object getData() {
		return data;
	}

	public Return<T> setData(Object data) {
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
