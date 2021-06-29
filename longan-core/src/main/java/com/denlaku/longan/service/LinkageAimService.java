package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.LinkageAimDao;
import com.denlaku.longan.vo.LinkageAim;

/**
 * @author tianx
 */
@Service
public class LinkageAimService {
	@Autowired
	private LinkageAimDao linkageAimDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.COMPONENT_LINKAGE_AIM);
	}
	
	public int batchAdd(List<LinkageAim> aims) {
		if (aims != null && !aims.isEmpty()) {
			aims.forEach(aim -> {
				String nextId = this.nextId();
				aim.setId(nextId);
			});
			return linkageAimDao.batchAdd(aims);
		}
		return 0;
	}

	public int delete(String linlageId) {
		return linkageAimDao.delete(linlageId);
	}

	public List<LinkageAim> list(String linlageId) {
		return linkageAimDao.list(linlageId);
	}
	
}
