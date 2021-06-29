package com.denlaku.longan.qo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataPowerNodeQuery extends Query {

	private String spaceId;
	private List<String> spaceIds;
	
	public static DataPowerNodeQuery of() {
		return new DataPowerNodeQuery();
	}
	
	public static DataPowerNodeQuery of(String id) {
		DataPowerNodeQuery query = new DataPowerNodeQuery();
		query.setId(id);
		return query;
	}
}
