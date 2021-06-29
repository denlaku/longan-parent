package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.CustomPageDao;
import com.denlaku.longan.qo.CustomPageQuery;
import com.denlaku.longan.vo.CustomPage;

/**
 * @author tianx
 */
@Service
public class CustomPageService {

	@Autowired
	private CustomPageDao customPageDao;

	public String nextId() {
		return SnowFlakeService.nextId(BizModule.CUSTOM_PAGE);
	}

	public int add(CustomPage customPage) {
		return customPageDao.add(customPage);
	}

	public int update(CustomPage customPage) {
		return customPageDao.update(customPage);
	}

	public int delete(String id) {
		CustomPageQuery query = CustomPageQuery.of(id);
		return customPageDao.delete(query);
	}

	public List<CustomPage> list(CustomPage customPage) {
		return customPageDao.list(customPage);
	}

	public CustomPage get(String id) {
		CustomPageQuery query = CustomPageQuery.of(id);
		return customPageDao.get(query);
	}

}
