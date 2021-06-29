package com.denlaku.longan.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.denlaku.longan.vo.DataPowerNode;

/**
 * @author tianx
 */
public class DataPowerNodeEventListener extends AnalysisEventListener<DataPowerNode> {

	private List<DataPowerNode> list = new ArrayList<>();

	@Override
	public void invoke(DataPowerNode dataPower, AnalysisContext context) {
		list.add(dataPower);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
	}

	public List<DataPowerNode> getList() {
		return list;
	}

}
