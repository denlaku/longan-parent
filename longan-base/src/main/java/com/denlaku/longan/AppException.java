package com.denlaku.longan;

/**
 * 应用异常
 *
 * @author tianx
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppException() {
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String toString() {
		return this.getLocalizedMessage();
	}

}
