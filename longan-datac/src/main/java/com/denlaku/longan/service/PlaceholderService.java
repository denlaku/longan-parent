package com.denlaku.longan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.PlaceholderDao;
import com.denlaku.longan.vo.Placeholder;

/**
 * @author tianx
 */
@Service
public class PlaceholderService {

	@Autowired
	private PlaceholderDao placeholderDao;

	public int add(Placeholder placeholder) {
		return placeholderDao.add(placeholder);
	}

	public int delete(String dataSetId) {
		return placeholderDao.delete(dataSetId);
	}

	public List<Placeholder> list(String dataSetId) {
		return placeholderDao.list(dataSetId);
	}
}
