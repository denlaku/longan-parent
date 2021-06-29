package com.denlaku.longan.service;

import java.util.List;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.CustomApiDao;
import com.denlaku.longan.qo.CustomApiQuery;
import com.denlaku.longan.vo.CustomApi;
import com.denlaku.longan.vo.CustomApiField;

/**
 * @author tianx
 */
@Service
public class CustomApiInfoService {

	@Autowired
	private CustomApiDao customApiDao;
	
	public String nextId() {
		return SnowFlakeService.nextId(BizModule.CUSTOM_API);
	}

	public int add(CustomApi customApi) {
		int add = customApiDao.add(customApi);
		this.addFields(customApi.getFields(), customApi.getId());
		return add;
	}

	public int update(CustomApi customApi) {
		String id = customApi.getId();
		this.deleteFields(id);
		this.addFields(customApi.getFields(), id);
		return customApiDao.update(customApi);
	}
	
	public int delete(String id) {
		CustomApiQuery query = CustomApiQuery.of(id);
		return customApiDao.delete(query);
	}

	public CustomApi get(String id) {
		CustomApiQuery query = CustomApiQuery.of(id);
		CustomApi customApi = customApiDao.get(query);
		Objects.ifPresent(customApi, api -> {
			List<CustomApiField> fields = this.listFields(id);
			api.setFields(fields);
		});
		return customApi;
	}

	public List<CustomApi> list(CustomApiQuery query) {
		return customApiDao.list(query);
	}
	
	public int addFields(List<CustomApiField> fields, String apiId) {
		if (Lists.isEmpty(fields)) {
			return 0;
		}
		fields.forEach(field -> {
			field.setApiId(apiId);
		});
		return customApiDao.addFields(fields);
	}
	
	public List<CustomApiField> listFields(String apiId) {
		return customApiDao.listFields(apiId);
	}
	
	public int deleteFields(String apiId) {
		return customApiDao.deleteFields(apiId);
	}
}
