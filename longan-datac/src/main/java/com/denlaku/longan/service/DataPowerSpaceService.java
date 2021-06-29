package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.DataPowerSpaceDao;
import com.denlaku.longan.qo.DataPowerSpaceQuery;
import com.denlaku.longan.vo.DataPowerSpace;

/**
 * @author tianx
 */
@Service
public class DataPowerSpaceService {
	@Autowired
	private DataPowerSpaceDao dataPowerSpaceDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.DATA_POWER_SPACE);
	}

	public int add(DataPowerSpace dataPowerSpace) {
		return dataPowerSpaceDao.add(dataPowerSpace);
	}

	public int update(DataPowerSpace dataPowerSpace) {
		return dataPowerSpaceDao.update(dataPowerSpace);
	}

	public int delete(DataPowerSpaceQuery query) {
		return dataPowerSpaceDao.delete(query);
	}

	public DataPowerSpace get(DataPowerSpaceQuery query) {
		return dataPowerSpaceDao.get(query);
	}

	public List<DataPowerSpace> list(DataPowerSpaceQuery query) {
		return dataPowerSpaceDao.list(query);
	}
}
