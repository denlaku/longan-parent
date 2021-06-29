package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.LinkageDao;
import com.denlaku.longan.vo.Linkage;
import com.denlaku.longan.vo.LinkageAim;

/**
 * @author tianx
 */
@Service
public class LinkageService {
	@Autowired
	private LinkageDao linkageDao;
	@Autowired
	private LinkageAimService linkageAimService;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.COMPONENT_LINKAGE);
	}
	
	public int add(Linkage linkage) {
		String id = this.nextId();
		linkage.setId(id);
		List<LinkageAim> aims = linkage.getAims();
		if (aims != null && !aims.isEmpty() ) {
			aims.forEach(aim -> {
				aim.setLinkageId(id);
			});
			linkageAimService.batchAdd(aims);
		}
		return linkageDao.add(linkage);
	}

	public int delete(String componentId) {
		List<Linkage> linkages = linkageDao.list(componentId);
		if (linkages != null && !linkages.isEmpty() ) {
			linkages.forEach(linkage -> {
				linkageAimService.delete(linkage.getId());
			});
		}
		return linkageDao.delete(componentId);
	}

	public List<Linkage> list(String componentId) {
		List<Linkage> linkages = linkageDao.list(componentId);
		if (linkages != null) {
			linkages.forEach(linkage -> {
				List<LinkageAim> aims = linkageAimService.list(linkage.getId());
				linkage.setAims(aims);
			});
		}
		return linkages;
	}
	
}
