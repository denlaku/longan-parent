package com.denlaku.longan.qo;

import com.denlaku.longan.vo.Component;
import com.denlaku.longan.vo.DataSet;

import lombok.Data;

/**
 * @author tianx
 */
@Data
public class RefreshQuery {
	
	private String id;
	private DataSet dataSet;
	private Component component;
	private boolean debug;
	private Integer pageNum;
	private Integer pageSize;
}
