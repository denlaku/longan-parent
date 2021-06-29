package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.ResourceDao;
import com.denlaku.longan.vo.Resource;

/**
 * @author tianx
 */
@Service
public class ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	public String nextId() {
		return SnowFlakeService.nextId(BizModule.RESOURCE);
	}

	public int add(Resource resource) {
		return resourceDao.add(resource);
	}

	public int delete(String id) {
		return resourceDao.delete(id);
	}

	public Resource get(String id) {
		return resourceDao.get(id);
	}

	public List<Resource> list(Resource resource) {
		return resourceDao.list(resource);
	}

}
