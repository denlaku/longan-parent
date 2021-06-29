package com.denlaku.longan.sql;

import java.util.ArrayList;
import java.util.List;

import com.denlaku.longan.vo.DataSet;

/**
 * @author tianx
 */
public class SqlInfo {

	private List<SelectItem> selectItems = new ArrayList<>();
	private String tableName;
	private List<WhereItem> whereItems = new ArrayList<>();
	private List<GroupByItem> groupByItems = new ArrayList<>();
	private List<HavingItem> havingItems = new ArrayList<>();
	private List<OrderByItem> orderByItems = new ArrayList<>();
	private Limit limit;

	private DataSet dataSet;

	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	public void addSelectItem(SelectItem item) {
		this.selectItems.add(item);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<WhereItem> getWhereItems() {
		return whereItems;
	}

	public void addWhereItem(WhereItem item) {
		if (item == null)
			return;
		this.whereItems.add(item);
	}

	public List<GroupByItem> getGroupByItems() {
		return groupByItems;
	}

	public void addGroupByItem(GroupByItem item) {
		if (item == null)
			return;
		this.groupByItems.add(item);
	}

	public List<HavingItem> getHavingItems() {
		return havingItems;
	}

	public void addHavingItem(HavingItem item) {
		if (item == null)
			return;
		this.havingItems.add(item);
	}

	public List<OrderByItem> getOrderByItems() {
		return orderByItems;
	}

	public void addOrderByItem(OrderByItem item) {
		if (item == null)
			return;
		this.orderByItems.add(item);
	}

	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

}
