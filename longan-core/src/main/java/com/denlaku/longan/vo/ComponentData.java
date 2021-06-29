package com.denlaku.longan.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.denlaku.longan.util.Lists;
import lombok.Data;

/**
 * @author tianx
 */
@Data
public class ComponentData {
	private boolean status;
	private String id;
	private List<Map<String, Object>> rows;
	private String sql;
	private List<Object> params;
	private List<Object> debugs;
	public void addDebug(Object debug) {
		if (debugs == null) {
			debugs = Lists.of();
		}
		debugs.add(debug);
	}
}
