package com.denlaku.longan.qo;

/**
 * @author tianx
 */
public class CustomPageQuery extends Query {

	public static CustomPageQuery of(String id) {
		CustomPageQuery customPageQuery = new CustomPageQuery();
		customPageQuery.setId(id);
		return customPageQuery;
	}
}
