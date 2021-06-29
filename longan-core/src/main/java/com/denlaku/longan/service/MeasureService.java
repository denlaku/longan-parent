package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.MeasureDao;
import com.denlaku.longan.vo.Measure;

/**
 * @author tianx
 */
@Service
public class MeasureService {
	@Autowired
	private MeasureDao measureDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.COMPONENT_MEASURE);
	}

	public int add(Measure measure) {
		String nextId = this.nextId();
		measure.setId(nextId);
		return measureDao.add(measure);
	}

	public int delete(String componentId) {
		return measureDao.delete(componentId);
	}

	public List<Measure> list(String componentId) {
		return measureDao.list(componentId);
	}
}
