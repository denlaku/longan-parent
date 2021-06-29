package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.FilterDao;
import com.denlaku.longan.vo.Filter;

/**
 * @author tianx
 */
@Service
public class FilterService {

	@Autowired
	private FilterDao filterDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.COMPONENT_FILTER);
	}
	
	public int add(Filter filter) {
		String nextId = this.nextId();
		filter.setId(nextId);
		return filterDao.add(filter);
	}

	public int delete(String componentId) {
		return filterDao.delete(componentId);
	}

	public List<Filter> list(String componentId) {
		return filterDao.list(componentId);
	}
	
}
