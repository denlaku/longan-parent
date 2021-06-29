package com.denlaku.longan.service;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import com.denlaku.longan.util.CommonSupplier;
import com.denlaku.longan.util.ForkJoinUtil;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.datac.SqlReturn;
import com.denlaku.longan.sql.SelectItem;
import com.denlaku.longan.sql.SqlInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Service
@Slf4j
public class CustomApiService {

	@Autowired
	private CustomApiInfoService customApiInfoService;
	@Autowired
	private DataSetService dataSetService;
	@Autowired
	private DataFetchService dataFetchService;

	public CustomApiData fetch(CustomApi apiInfo) {
		CustomApi customApi = customApiInfoService.get(apiInfo.getId());
		String dataSetId = customApi.getDataSetId();
		DataSet dataSet = dataSetService.get(dataSetId);

		SqlInfo info = new SqlInfo();
		info.setDataSet(dataSet);
		info.setTableName(newTableName(dataSet));
		List<DataSetField> fields = dataSet.getFields();
		if (fields != null) {
			fields.forEach(field -> {
				SelectItem selectItem = newSelectItem(field);
				info.addSelectItem(selectItem);
			});
		}

		SqlReturn query = dataFetchService.query(info);
		List<Map<String, Object>> rows = query.getRows();

		CustomApiData apiData = new CustomApiData();
		apiData.setId(apiInfo.getId());
		apiData.setRows(rows);

		return apiData;

	}

	private SelectItem newSelectItem(DataSetField field) {
		SelectItem item = new SelectItem();
		item.setName(field.getName());
		return item;
	}

	private String newTableName(DataSet dataSet) {
		String command = dataSet.getCommand();
		return String.format("(%s) t_mid_165631313", command);
	}

	public List<CustomApiData> fetches(List<CustomApi> customApis) {
		int size = customApis.size();
		if (size == 1) {
			CustomApi customApi = customApis.get(0);
			CustomApiData apiData = this.fetch(customApi);
			return Lists.of(apiData);
		}
		try {
			List<CommonSupplier<CustomApiData>> suppliers = Lists.ofSize(size);
			customApis.forEach(apiInfo -> suppliers.add(() -> this.fetch(apiInfo)));
			List<CustomApiData> list = ForkJoinUtil.submit(suppliers);
			return list;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Lists.of();
	}
	
}
