package com.denlaku.longan.datac;

import java.util.List;
import java.util.Map;

import com.denlaku.longan.Column;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public class SqlReturn {

	private boolean status = true;
	private String sql;
	private List<Object> params;
	private List<Map<String, Object>> rows;
	private List<Column> metas;
	private Throwable throwable;
	private long start = System.currentTimeMillis();
	private long end;

	public long getDuration() {
		return end - start;
	}

	public String getMessage() {
		if (throwable != null) {
			return throwable.getMessage();
		}
		return null;
	}

}
