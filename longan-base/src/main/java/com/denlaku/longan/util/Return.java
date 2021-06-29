package com.denlaku.longan.util;

import com.denlaku.longan.vo.User;

import java.util.Map;

/**
 * @author tianx
 */
public interface Return<T> {

	Return<T> setCode(String code);

	default Object getData() {
		return null;
	}

	default boolean getStatus() {
		return false;
	}

	default Return<T> setExt(Object ext) {
		return null;
	}

	default Return<T> setUmap(Map<String, User> umap) {
		return null;
	}

}
