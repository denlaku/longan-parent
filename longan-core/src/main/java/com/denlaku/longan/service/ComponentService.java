package com.denlaku.longan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.denlaku.longan.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.ComponentDao;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Strings;
import com.denlaku.longan.vo.Component;
import com.denlaku.longan.vo.ComponentCache;
import com.denlaku.longan.vo.Dimension;
import com.denlaku.longan.vo.Filter;
import com.denlaku.longan.vo.Goingin;
import com.denlaku.longan.vo.Linkage;
import com.denlaku.longan.vo.Measure;

/**
 * @author tianx
 */
@Service
public class ComponentService {

	@Autowired
	private ComponentDao componentDao;

	@Autowired
	private DimensionService dimensionService;

	@Autowired
	private MeasureService measureService;

	@Autowired
	private LinkageService linkageService;

	@Autowired
	private GoinginService goinginService;
	
	@Autowired
	private FilterService filterService;

	public String nextId() {
		return SnowFlakeService.nextId("component");
	}

	public int add(Component component) {
		int add = componentDao.add(component);
		addDimensions(component);
		addMeasures(component);
		addLinkages(component);
		addGoingins(component);
		addFilters(component);
		this.addCache(component);
		return add;
	}

	private void addDimensions(Component component) {
		String componentId = component.getId();
		List<Dimension> dimensions = component.getDimensions();
		if (dimensions == null || dimensions.isEmpty()) {
			return;
		}
		dimensions.forEach(dimension -> {
			dimension.setComponentId(componentId);
			dimensionService.add(dimension);
		});
	}

	private void addMeasures(Component component) {
		String componentId = component.getId();
		List<Measure> measures = component.getMeasures();
		if (Lists.isEmpty(measures)) {
			return;
		}
		measures.forEach(measure -> {
			measure.setComponentId(componentId);
			measureService.add(measure);
		});
	}

	private void addLinkages(Component component) {
		String componentId = component.getId();
		List<Linkage> linkages = component.getLinkages();
		if (Lists.isEmpty(linkages)) {
			return;
		}
		linkages.forEach(linkage -> {
			linkage.setComponentId(componentId);
			linkageService.add(linkage);
		});
	}

	private void addGoingins(Component component) {
		String componentId = component.getId();
		List<Goingin> goingins = component.getGoingins();
		if (Lists.isEmpty(goingins)) {
			return;
		}
		goingins.forEach(goingin -> {
			goingin.setComponentId(componentId);
			goinginService.add(goingin);
		});
	}
	
	private void addFilters(Component component) {
		String componentId = component.getId();
		List<Filter> filters = component.getFilters();
		if (Lists.isEmpty(filters)) {
			return;
		}
		filters.forEach(filter -> {
			filter.setComponentId(componentId);
			filterService.add(filter);
		});
	}

	public int delete(String id) {
		int delete = componentDao.delete(id);
		if (delete != 0) {
			componentDao.deleteCache(id);
			dimensionService.delete(id);
			measureService.delete(id);
			linkageService.delete(id);
			goinginService.delete(id);
			filterService.delete(id);
		}
		return delete;
	}

	public int batchDelete(String dashboardId) {
		List<Component> components = this.list(dashboardId);
		if (Lists.isNotEmpty(components)) {
			components.forEach(component -> {
				this.delete(component.getId());
			});
			return components.size();
		}
		return 0;
	}

	public Component get(String id) {
		Component component = componentDao.get(id);
		if (component != null) {
			dimensionService.list(id);
			measureService.list(id);
			linkageService.list(id);
			goinginService.list(id);
		}
		return component;
	}

	public List<Component> list(String dashboardId) {
		return componentDao.list(dashboardId);
	}

	public List<Component> listCache(String dashboardId) {
		List<ComponentCache> caches = componentDao.listCache(dashboardId);
		List<Component> components = Lists.ofSize(caches.size());
		if (Lists.isNotEmpty(caches)) {
			List<Component> list = Lists.ofSize(caches.size());
			Map<String, List<Component>> map = Maps.ofSize(caches.size());
			caches.forEach(cache -> {
				Component component = cache.getComponent();
				if (component != null) {
					list.add(component);
					String parentId = component.getParentId();
					if (Strings.isNotEmpty(parentId)) {
						List<Component> subs = map.get(parentId);
						if (subs == null) {
							subs = Lists.ofSize(6);
							map.put(parentId, subs);
						}
						subs.add(component);
					}
				}
			});
			list.forEach(component -> {
				String id = component.getId();
				String parentId = component.getParentId();
				if (Strings.isEmpty(parentId)) {
					components.add(component);
				}
				component.setComponents(map.get(id));
			});
		}
		return components;
	}

	public Component getCache(String id) {
		ComponentCache cache = componentDao.getCache(id);
		if (cache != null) {
			return cache.getComponent();
		}
		return null;
	}

	public int addCache(Component component) {
		ComponentCache cache = new ComponentCache();
		component.setComponents(null);
		cache.setId(component.getId());
		cache.setComponent(component);
		cache.setDashboardId(component.getDashboardId());
		cache.setTenantId(component.getTenantId());
		return componentDao.addCache(cache);
	}

	public int deleteCache(String id) {
		return componentDao.deleteCache(id);
	}

	public Map<String, Object> refresh() {
		return null;
	}

	public List<Map<String, Object>> batchRefresh() {
		return null;
	}
}
