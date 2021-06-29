package com.denlaku.longan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.util.Maps;
import com.denlaku.longan.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denlaku.longan.dao.DataSetDao;

import cn.hutool.core.collection.CollUtil;

/**
 * @author tianx
 */
@Service
public class DataSetService {

	private static final String MATCH_FIELD = "1";
	private static final  String MATCH_PLACEHOLDER = "2";

	@Autowired
	private DataSetDao dataSetDao;

	@Autowired
	private MetaDataService metaDataService;

	@Autowired
	private DataSourceService dataSourceService;

	@Autowired
	private DataSetFieldService dataSetFieldService;

	@Autowired
	private PlaceholderService placeholderService;
	
	@Autowired
	private PowerMatchService powerMatchService;

	@Autowired
	private BizTagService bizTagService;

	public String nextId() {
		return SnowFlakeService.nextId(BizModule.DATA_SET);
	}

	@Transactional(rollbackFor = Exception.class)
	public int add(DataSet dataSet) {
		int add = dataSetDao.add(dataSet);
		addFields(dataSet);
		this.addPlaceholder(dataSet);
		this.addCache(dataSet);
		return add;
	}

	public int update(DataSet dataSet) {
		int update = dataSetDao.update(dataSet);
		String id = dataSet.getId();
		dataSetFieldService.delete(id);
		placeholderService.delete(id);
		powerMatchService.delete(id);
		this.addFields(dataSet);
		this.addPlaceholder(dataSet);
		this.updateCache(dataSet);
		return update;
	}

	private int addFields(DataSet dataSet) {
		String dataSetId = dataSet.getId();
		List<DataSetField> fields = dataSet.getFields();
		if (fields == null || fields.isEmpty()) {
			return 0;
		}
		fields.forEach(field -> {
			field.setDataSetId(dataSetId);
			String nextId = dataSetFieldService.nextId();
			field.setId(nextId);
			dataSetFieldService.add(field);
			String id = field.getId();
			List<PowerMatch> powerMatchs = field.getPowerMatchs();
			if (powerMatchs != null) {
				powerMatchs.forEach(powerMatch -> {
					powerMatch.setDataSetId(dataSetId);
					powerMatch.setOfId(id);
					powerMatch.setOfType("1");
					powerMatch.setId(dataSetId);
					powerMatchService.add(powerMatch);
				});
			}
		});
		return fields.size();
	}

	private int addPlaceholder(DataSet dataSet) {
		List<Placeholder> placeholders = dataSet.getPlaceholders();
		if (placeholders == null || placeholders.isEmpty()) {
			return 0;
		}
		String dataSetId = dataSet.getId();
		placeholders.forEach(placeholder -> {
			placeholder.setDataSetId(dataSetId);
			placeholderService.add(placeholder);
			String id = placeholder.getId();
			List<PowerMatch> powerMatchs = placeholder.getPowerMatchs();
			if (powerMatchs != null) {
				powerMatchs.forEach(powerMatch -> {
					powerMatch.setDataSetId(dataSetId);
					powerMatch.setOfId(id);
					powerMatch.setOfType("2");
					powerMatch.setId(dataSetId);
					powerMatchService.add(powerMatch);
				});
			}
		});
		return placeholders.size();
	}

	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) {
		int delete = dataSetDao.delete(id);
		dataSetFieldService.delete(id);
		placeholderService.delete(id);
		powerMatchService.delete(id);
		return delete;
	}

	public List<DataSet> list(DataSet dataSet) {
		return dataSetDao.list(dataSet);
	}

	public DataSet get(String id) {
		DataSet dataSet = dataSetDao.get(id);
		if (dataSet != null) {
			List<DataSetField> fields = dataSetFieldService.list(id);
			dataSet.setFields(fields);
			List<Placeholder> placeholders = placeholderService.list(id);
			dataSet.setPlaceholders(placeholders);
			List<PowerMatch> powerMatches = powerMatchService.list(id);
			setPowerMatches(dataSet, powerMatches);
		}
		return dataSet;
	}
	
	private void setPowerMatches(DataSet dataSet, List<PowerMatch> powerMatchs) {
		if (CollUtil.isEmpty(powerMatchs)) {
			return;
		}
		int size = powerMatchs.size();
		Map<String, List<PowerMatch>> map1 = Maps.ofSize(size);
		Map<String, List<PowerMatch>> map2 = new HashMap<>();
		powerMatchs.forEach(powerMatch -> {
			String ofType = powerMatch.getOfType();
			String ofId = powerMatch.getOfId();
			if (MATCH_FIELD.equals(ofType)) {
				List<PowerMatch> pms = map1.get(ofId);
				if (pms == null) {
					pms = new ArrayList<>();
					map1.put(ofId, pms);
				}
				pms.add(powerMatch);
			}
			if (MATCH_PLACEHOLDER.equals(ofType)) {
				List<PowerMatch> pms = map2.get(ofId);
				if (pms == null) {
					pms = new ArrayList<>();
					map2.put(ofId, pms);
				}
				pms.add(powerMatch);
			}
		});
		List<DataSetField> fields = dataSet.getFields();
		if (CollUtil.isNotEmpty(fields)) {
			fields.forEach(field -> {
				String id = field.getId();
				field.setPowerMatchs(map1.get(id));
			});
		}
		List<Placeholder> placeholders = dataSet.getPlaceholders();
		if (CollUtil.isNotEmpty(placeholders)) {
			placeholders.forEach(placeholder -> {
				String id = placeholder.getId();
				placeholder.setPowerMatchs(map2.get(id));
			});
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public int addCache(DataSet dataSet) {
		DataSetCache cache = new DataSetCache();
		cache.setId(dataSet.getId());
		cache.setDataSet(dataSet);
		return dataSetDao.addCache(cache);
	}

	@Transactional(rollbackFor = Exception.class)
	public int updateCache(DataSet dataSet) {
		DataSetCache cache = new DataSetCache();
		cache.setId(dataSet.getId());
		cache.setDataSet(dataSet);
		return dataSetDao.updateCache(cache);
	}

	@Transactional(rollbackFor = Exception.class)
	public int deleteCache(String id) {
		return dataSetDao.deleteCache(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public DataSet getCache(String id) {
		DataSetCache cache = dataSetDao.getCache(id);
		if (cache != null) {
			return cache.getDataSet();
		} else {
			DataSet dataSet = this.get(id);
			if (dataSet != null) {
				this.addCache(dataSet);
			}
			return dataSet;
		}
	}

	public List<MetaData> listMetaData(DataSet dataSet) {
		String dataSourceId = dataSet.getDataSourceId();
		DataSourceBean dataSourceBean = dataSourceService.get(dataSourceId);
		String password = dataSourceService.getPassword(dataSourceId);
		dataSourceBean.setPassword(password);
		List<MetaData> list = metaDataService.list(dataSourceBean, dataSet.getCommand());
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	public int addTag(BizTag tag) {
		tag.setBiz(BizModule.DATA_SET);
		return bizTagService.add(tag);
	}

	@Transactional(rollbackFor = Exception.class)
	public int deleteTag(BizTagQuery query) {
		query.setBiz(BizModule.DATA_SET);
		return bizTagService.delete(query);
	}

	public List<BizTag> listTag(BizTagQuery query) {
		query.setBiz(BizModule.DATA_SET);
		return bizTagService.list(query);
	}

}
