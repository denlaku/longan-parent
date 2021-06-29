package com.denlaku.longan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @author tianx
 */
public class MyEventListener extends AnalysisEventListener<Map<Integer, String>> {

	List<Map<Integer, String>> list = new ArrayList<>();
	Map<Integer, String> head = new HashMap<>();

	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		head = headMap;
	}

	@Override
	public void invoke(Map<Integer, String> data, AnalysisContext context) {
		System.out.println(data);
		list.add(data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.err.println("MyEventListener.doAfterAllAnalysed()");
	}

	public List<Map<Integer, String>> getList() {
		return list;
	}
	
	public Map<Integer, String> getHead() {
		return head;
	}

}
