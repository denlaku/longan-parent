package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.DimensionDao;
import com.denlaku.longan.vo.Dimension;

/**
 * @author tianx
 */
@Service
public class DimensionService {

	@Autowired
	private DimensionDao dimensionDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.COMPONENT_DIMENSION);
	}

	public int add(Dimension dimension) {
		String nextId = this.nextId();
		dimension.setId(nextId);
		return dimensionDao.add(dimension);
	}

	public int delete(String componentId) {
		return dimensionDao.delete(componentId);
	}

	public List<Dimension> list(String componentId) {
		return dimensionDao.list(componentId);
	}

}
