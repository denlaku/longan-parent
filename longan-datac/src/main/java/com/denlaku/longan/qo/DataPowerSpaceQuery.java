package com.denlaku.longan.qo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataPowerSpaceQuery extends Query {

	
	private String name;
	
	public static DataPowerSpaceQuery of(String id) {
		DataPowerSpaceQuery query = new DataPowerSpaceQuery();
		query.setId(id);
		return query;
	}
}
