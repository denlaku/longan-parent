package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.PowerMatchDao;
import com.denlaku.longan.vo.PowerMatch;

/**
 * @author tianx
 */
@Service
public class PowerMatchService {

	public String nextId() {
		return SnowFlakeService.nextId(BizModule.POWER_MATCH);
	}
	
	@Autowired
	private PowerMatchDao powerMatchDao;
	
	public int add(PowerMatch powerMatch) {
		powerMatch.setId(nextId());
		return powerMatchDao.add(powerMatch);
	}
	
	public int delete(String dataSetId) {
		return powerMatchDao.delete(dataSetId);
	}
	
	public List<PowerMatch> list(String dataSetId) {
		return powerMatchDao.list(dataSetId);
	}
}
