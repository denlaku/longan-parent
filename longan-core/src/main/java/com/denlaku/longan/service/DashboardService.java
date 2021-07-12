package com.denlaku.longan.service;

import java.util.List;
import java.util.Map;

import com.denlaku.longan.BizModule;
import com.denlaku.longan.Current;
import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Maps;
import com.denlaku.longan.util.Objects;
import com.denlaku.longan.util.Strings;
import com.denlaku.longan.vo.BizTag;
import com.denlaku.longan.vo.DashboardHis;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denlaku.longan.dao.DashboardDao;
import com.denlaku.longan.vo.Component;
import com.denlaku.longan.vo.Dashboard;

/**
 * @author tianx
 */
@Service
public class DashboardService {

	@Autowired
	private DashboardDao dashboardDao;

	@Autowired
	private ComponentService componentService;

	@Autowired
	private DashboardHisService dashboardHisService;

	@Autowired
	private BizTagService bizTagService;

	@Autowired
	private IndexService indexService;

	public String nextId() {
		return SnowFlakeService.nextId("dashboard");
	}
	
	@Transactional
	public int add(Dashboard dashboard) {
		int add = dashboardDao.add(dashboard);
		addComponents(dashboard.getId(), null, dashboard.getComponents());
		return add;
	}

	private void addComponents(String dashboardId, String parentId, List<Component> components) {
		if (components != null) {
			components.forEach(component -> {
				component.setDashboardId(dashboardId);
				component.setParentId(parentId);
				List<Component> children = component.getComponents();
				componentService.add(component);
				addComponents(dashboardId, component.getId(), children);
			});
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int update(Dashboard dashboard) {
		Dashboard old = this.get(dashboard.getId());
		DashboardHis dashboardHis = newDashboardHis(old);
		dashboardHisService.add(dashboardHis);

		int update = dashboardDao.update(dashboard);
		String id = dashboard.getId();
		componentService.batchDelete(id);
		addComponents(id, null, dashboard.getComponents());
		return update;
	}

	private DashboardHis newDashboardHis(Dashboard dashboard) {
		if (dashboard != null && Strings.isNotEmpty(dashboard.getId())) {
			DashboardHis dashboardHis = new DashboardHis();
			dashboardHis.setBizId(dashboard.getId());
			dashboardHis.setDashboard(dashboard);
			return dashboardHis;
		}
		return null;
	}

	@Transactional(rollbackFor=Exception.class)
	public int delete(String id) {
		int update = dashboardDao.delete(id);
		componentService.batchDelete(id);
		return update;
	}
	
	public boolean exists(String id) {
		return this.get(id) != null;
	}

	public Dashboard get(String id) {
		Dashboard dashboard = dashboardDao.get(id);
		if (dashboard != null) {
			List<Component> components = componentService.listCache(id);
			dashboard.setComponents(components);
		}
		return dashboard;
	}

	public List<Dashboard> list(Dashboard dashboard) {
		return dashboardDao.list(dashboard);
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

	public List<Map<String, String>> search(String keyword) {
		IndexQuery indexQuery = buildIndexQuery();
		indexQuery.setFields(Lists.of("title"));
		indexQuery.setKeyword(keyword);
		List<Document> documents = indexService.search(indexQuery);
		List<Map<String, String>> result = Lists.ofSize(documents.size());
		documents.forEach(document -> {
			List<IndexableField> fields = document.getFields();
			Map<String, String> map = Lists.toMap(fields, IndexableField::name, IndexableField::stringValue);
			result.add(map);
		});
		return result;
	}

	public void buildIndex(Dashboard dashboard) {
		IndexHolder holder = new IndexHolder();
		holder.setNamespace(dashboard.getTenantId());
		holder.setBiz(BizModule.DASHBOARD);
		Document document = buildDocument(dashboard);
		holder.addDocument(document);
		this.deleteIndex(dashboard);
		indexService.index(holder);
	}

	private void deleteIndex(Dashboard dashboard) {
		IndexQuery indexQuery = buildIndexQuery();
		indexQuery.setFields(Lists.of("id"));
		indexQuery.setKeyword(dashboard.getId());
		indexService.delete(indexQuery);
	}

	private IndexQuery buildIndexQuery() {
		IndexQuery indexQuery = new IndexQuery();
		indexQuery.setNamespace(Current.getTenantId());
		indexQuery.setBiz(BizModule.DASHBOARD);
		return indexQuery;
	}

	private Document buildDocument(Dashboard dashboard) {
		Document document = new Document();
		FieldType idType = new FieldType();
		idType.setTokenized(false);
		idType.setStored(true);
		idType.setIndexOptions(IndexOptions.DOCS);
		Field idField = new Field("id", dashboard.getId(), idType);
		document.add(idField);

		FieldType nameType = new FieldType();
		nameType.setTokenized(true);
		nameType.setStored(true);
		nameType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		Field nameField = new Field("title", dashboard.getTitle(), nameType);
		document.add(nameField);

		FieldType terminalType = new FieldType();
		terminalType.setTokenized(false);
		terminalType.setStored(true);
		Field terminalField = new Field("terminal", dashboard.getTerminal(), terminalType);
		document.add(terminalField);

		FieldType bizType = new FieldType();
		bizType.setTokenized(false);
		bizType.setStored(true);
		Field bizField = new Field("biz", "dashboard", bizType);
		document.add(bizField);

		Objects.ifPresent(dashboard.getDesc(), desc -> {
			FieldType descType = new FieldType();
			descType.setTokenized(true);
			descType.setStored(true);
			descType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			Field descField = new Field("desc", desc, descType);
			document.add(descField);
		});

		return document;
	}

}
