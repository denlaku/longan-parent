package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.DataSetFieldDao;
import com.denlaku.longan.vo.DataSetField;

/**
 * @author tianx
 */
@Service
public class DataSetFieldService {

	@Autowired
	private DataSetFieldDao dataSetFieldDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.DATA_SET_FIELD);
	}

	public int add(DataSetField field) {
		return dataSetFieldDao.add(field);
	}

	public int delete(String dataSetId) {
		return dataSetFieldDao.delete(dataSetId);
	}

	public List<DataSetField> list(String dataSetId) {
		return dataSetFieldDao.list(dataSetId);
	}
}
