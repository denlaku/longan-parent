package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.DataPowerNodeDao;
import com.denlaku.longan.qo.DataPowerNodeQuery;
import com.denlaku.longan.vo.DataPowerNode;

/**
 * @author tianx
 */
@Service
public class DataPowerNodeService {
	@Autowired
	private DataPowerNodeDao dataPowerNodeDao;

	public String nextId() {
		return SnowFlakeService.nextId(BizModule.DATA_POWER_NODE);
	}

	public int batchAdd(List<DataPowerNode> dataPowerNodes) {
		return dataPowerNodeDao.batchAdd(dataPowerNodes);
	}

	public int update(DataPowerNode dataPowerNode) {
		return dataPowerNodeDao.update(dataPowerNode);
	}

	public int delete(DataPowerNodeQuery query) {
		return dataPowerNodeDao.delete(query);
	}

	public DataPowerNode get(DataPowerNodeQuery query) {
		return dataPowerNodeDao.get(query);
	}

	public List<DataPowerNode> list(DataPowerNodeQuery query) {
		return dataPowerNodeDao.list(query);
	}
}
