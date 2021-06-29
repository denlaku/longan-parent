package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.GoinginDao;
import com.denlaku.longan.vo.Goingin;

/**
 * @author tianx
 */
@Service
public class GoinginService {
	@Autowired
	private GoinginDao goinginDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.COMPONENT_GOINGIN);
	}

	public int add(Goingin goingin) {
		String nextId = this.nextId();
		goingin.setId(nextId);
		return goinginDao.add(goingin);
	}

	public int delete(String componentId) {
		return goinginDao.delete(componentId);
	}

	public List<Goingin> list(String componentId) {
		return goinginDao.list(componentId);
	}
}
