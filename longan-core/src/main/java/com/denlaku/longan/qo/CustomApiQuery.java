package com.denlaku.longan.qo;

/**
 * @author tianx
 */
public class CustomApiQuery extends Query {

	public static CustomApiQuery of(String id) {
		CustomApiQuery customPageQuery = new CustomApiQuery();
		customPageQuery.setId(id);
		return customPageQuery;
	}
}
