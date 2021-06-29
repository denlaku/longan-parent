package com.denlaku.longan.service;

import com.denlaku.longan.datac.SqlReturn;
import com.denlaku.longan.qo.DataPowerNodeQuery;
import com.denlaku.longan.qo.RefreshQuery;
import com.denlaku.longan.sql.*;
import com.denlaku.longan.util.*;
import com.denlaku.longan.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianx
 */
@Service
@Slf4j
public class RefreshService {

	@Autowired
	private DataFetchService dataFetchService;
	@Autowired
	private DataSetService dataSetService;
	@Autowired
	private ComponentService componentService;
	@Autowired
	private DataPowerNodeService dataPowerNodeService;

	public ComponentData refresh(RefreshQuery query) {
		boolean debug = query.isDebug();
		trimQuery(query);
		Component component = query.getComponent();
		ComponentData data = new ComponentData();
		if (component.getDataSetId() == null) {
			return data;
		}
		List<Dimension> dimensions = component.getDimensions();
		List<Measure> measures = component.getMeasures();
		List<Filter> filters = component.getFilters();
		SqlInfo info = new SqlInfo();
		if (Lists.isNotEmpty(dimensions)) {
			dimensions.forEach(dimension -> {
				SelectItem selectItem = newSelectItem(dimension);
				info.addSelectItem(selectItem);
				Order order = dimension.getOrder();
				OrderByItem orderByItem = newOrderByItem(order);
				info.addOrderByItem(orderByItem);
				GroupByItem groupByItem = newGroupByItem(dimension);
				info.addGroupByItem(groupByItem);

			});
		}
		if (Lists.isNotEmpty(measures)) {
			measures.forEach(measure -> {
				String aggregator = measure.getAggregator();
				measure.setAggregator(aggregator);
				SelectItem selectItem = newSelectItem(measure);
				info.addSelectItem(selectItem);
				Order order = measure.getOrder();
				OrderByItem orderByItem = newOrderByItem(order);
				info.addOrderByItem(orderByItem);
				if (Strings.isEmpty(aggregator)) {
					GroupByItem groupByItem = newGroupByItem(measure);
					info.addGroupByItem(groupByItem);
				}
			});
		}

		if (Lists.isNotEmpty(filters)) {
			filters.forEach(filter -> {
				List<Object> values = filter.getValues();
				if (Lists.isNotEmpty(values)) {
					String operator = filter.getOperator();
					WhereItem item = new WhereItem();
					item.setOperator(operator);
					item.setValues(values);
					item.setName(filter.getName());
					info.addWhereItem(item);
				}
			});
		}

		DataSet dataSet = query.getDataSet();
		List<DataSetField> fields = dataSet.getFields();
		if (Lists.isNotEmpty(fields)) {
			List<DataSetField> powerFields = new ArrayList<>();
			List<String> spaceIds = new ArrayList<>();
			fields.forEach(field -> {
				List<PowerMatch> powerMatches = field.getPowerMatchs();
				if (Lists.isNotEmpty(powerMatches)) {
					powerFields.add(field);
					powerMatches.forEach(powerMatch -> {
						String spaceId = powerMatch.getSpaceId();
						if (!spaceIds.contains(spaceId)) {
							spaceIds.add(spaceId);
						}
					});
				}
			});
			if (!spaceIds.isEmpty()) {
				DataPowerNodeQuery dataPowerNodeQuery = DataPowerNodeQuery.of();
				dataPowerNodeQuery.setSpaceIds(spaceIds);
				List<DataPowerNode> dataPowerNodes = dataPowerNodeService.list(dataPowerNodeQuery);
				Map<String, List<DataPowerNode>> nodeMap = Maps.ofSize(dataPowerNodes.size());
				if (dataPowerNodes != null) {
					dataPowerNodes.forEach(dataPowerNode -> {
						String spaceId = dataPowerNode.getSpaceId();
						List<DataPowerNode> nodes = nodeMap.get(spaceId);
						if (nodes == null) {
							nodes = new ArrayList<>();
							nodeMap.put(spaceId, nodes);
						}
						nodes.add(dataPowerNode);
					});
				}
				powerFields.forEach(field -> {
					List<Object> values = new ArrayList<>();
					List<PowerMatch> powerMatches = field.getPowerMatchs();
					for (PowerMatch pm: powerMatches) {
						String spaceId = pm.getSpaceId();
						List<DataPowerNode> nodes = nodeMap.get(spaceId);
						if (nodes == null) {
							return;
						}
						String type = pm.getType();
						int code = pm.getCode();
						int name = pm.getName();
						int reserve = pm.getReserve();
						nodes.forEach(node -> {
							String nodeType = node.getType();
							if (!nodeType.equals(type)) {
								return;
							}
							if (code == 1 && Strings.isNotEmpty(node.getCode())) {
								values.add(node.getCode());
							}
							if (name == 1 && Strings.isNotEmpty(node.getName())) {
								values.add(node.getName());
							}
							if (reserve == 1 && Strings.isNotEmpty(node.getReserve())) {
								values.add(node.getReserve());
							}
						});
					}
					WhereItem item = new WhereItem();
					item.setOperator("=");
					item.setValues(values);
					item.setName(field.getName());
					info.addWhereItem(item);
				});
			}
		}
		
		info.setTableName(newTableName(dataSet));
		info.setDataSet(dataSet);

		Integer pageNum = query.getPageNum();
		Integer pageSize = query.getPageSize();
		if (pageNum != null && pageSize != null) {
			Limit limit = new Limit();
			int start = (pageNum - 1) * pageSize;
			limit.setStart(start);
			limit.setSize(pageSize);
			info.setLimit(limit);
		}

		SqlReturn sqlReturn = dataFetchService.query(info);

		data.setStatus(sqlReturn.isStatus());
		data.setId(getId(query));
		data.setRows(sqlReturn.getRows());
		if (debug) {
			data.setSql(sqlReturn.getSql());
			data.setParams(sqlReturn.getParams());
			data.addDebug(sqlReturn.getThrowable());
		}

		return data;
	}

	private String getId(RefreshQuery query) {
		String id = query.getId();
		if (Strings.isEmpty(id)) {
			Component component = query.getComponent();
			if (component != null) {
				id = component.getId();
			}
		}
		return id;
	}

	public List<ComponentData> refreshes(List<RefreshQuery> queries, boolean debug) {
		int size = queries.size();
		if (size == 1) {
			RefreshQuery query = queries.get(0);
			query.setDebug(debug);
			ComponentData refresh = this.refresh(query);
			return Lists.of(refresh);
		}
		try {

			List<CommonSupplier<ComponentData>> suppliers = Lists.ofSize(size);
			queries.forEach(query -> suppliers.add(() -> {
					query.setDebug(debug);
					return this.refresh(query);
				})
			);
			List<ComponentData> list = ForkJoinUtil.submit(suppliers);
			return list;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Lists.empty();
	}

	private void trimQuery(RefreshQuery query) {
		Component component = query.getComponent();
		if (component == null) {
			String id = query.getId();
			component = componentService.getCache(id);
		}
		if (component == null) {
			return;
		}
		String dataSetId = component.getDataSetId();
		if (dataSetId != null) {
			DataSet dataSet = dataSetService.getCache(dataSetId);
			query.setDataSet(dataSet);
		}
	}

	private GroupByItem newGroupByItem(Dimension dimension) {
		GroupByItem item = new GroupByItem();
		item.setName(dimension.getName());
		return item;
	}
	
	private GroupByItem newGroupByItem(Measure measure) {
		GroupByItem item = new GroupByItem();
		item.setName(measure.getName());
		return item;
	}

	private SelectItem newSelectItem(Dimension dimension) {
		SelectItem item = new SelectItem();
		item.setName(dimension.getName());
		return item;
	}

	private SelectItem newSelectItem(Measure measure) {
		SelectItem item = new SelectItem();
		item.setName(measure.getName());
		item.setAggregator(measure.getAggregator());
		return item;
	}

	private String newTableName(DataSet dataSet) {
		String type = dataSet.getType();
		String command = dataSet.getCommand();
		if ("2".equals(type)) {
			return command;
		}
		return String.format("(%s) t_mid_165631313", command);
	}

	private OrderByItem newOrderByItem(Order order) {
		if (order == null) {
			return null;
		}
		OrderByItem item = new OrderByItem();
		item.setName(order.getName());
		item.setOrder(order.getPolicy());
		item.setValues(order.getValues());
		return item;
	}

	public Map<String, Object> view(RefreshQuery query) {
		return null;
	}

	public List<Map<String, Object>> batchView(List<RefreshQuery> queries) {
		return null;
	}
}
