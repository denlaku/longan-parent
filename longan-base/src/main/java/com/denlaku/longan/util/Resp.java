package com.denlaku.longan.util;

import java.util.List;

import com.github.pagehelper.Page;

/**
 * @author tianx
 */
public class Resp {

	public static <T> Return<T> success() {
		return success((T) null, null);
	}

	public static <T> Return<T> success(T data) {
		return success(data, null);
	}

	public static <T> Return<T> message(String message) {
		return success((T) null, message);
	}

	public static <T> Return<T> success(T data, String message) {
		return new Success<>(data, message);
	}

	public static <T> Return<List<T>> success(List<T> data, String message) {
		if (data instanceof Page) {
			return new PageSuccess<>(data, message);
		}
		return new Success<>(data, message);
	}

	public static <T> Return<List<T>> success(List<T> data) {
		return success(data, null);
	}

	public static <T> Return<T> error(String message) {
		return error(message, null);
	}

	public static <T> Return<T> error(String message, Object data) {
		return new Error<>(message, data);
	}

}
