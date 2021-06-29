package com.denlaku.longan.sql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Strings;

/**
 * @author tianx
 */
@Component
public class SqlCommandBuilder {

	public SqlCommand build(SqlInfo info) {
		SqlCommand sqlCommand = new SqlCommand();
		buildSql(info, sqlCommand);
		return sqlCommand;
	}

	private void buildSql(SqlInfo info, SqlCommand sqlCommand) {
		List<SelectItem> selectItems = info.getSelectItems();
		String tableName = info.getTableName();
		List<WhereItem> whereItems = info.getWhereItems();
		List<GroupByItem> groupByItems = info.getGroupByItems();
		List<HavingItem> havingItems = info.getHavingItems();
		List<OrderByItem> orderByItems = info.getOrderByItems();
		Limit limit = info.getLimit();

		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sb.append("select ");
		if (selectItems != null) {
			int size = selectItems.size();
			for (int i = 0; i < size; i++) {
				buildSelect(selectItems.get(i), sb);
				if (i != size - 1) {
					sb.append(", ");
				}
				sb.append("\n");
			}
		}
		sb.append("from ");
		sb.append(tableName);

		if (whereItems != null && !whereItems.isEmpty()) {
			sb.append("\n");
			sb.append(" where ");
			for (int i = 0; i < whereItems.size(); i++) {
				if (i != 0) {
					sb.append("\n");
					sb.append("and ");
				}
				buildWhere(whereItems.get(i), sb, params);
			}
		}

		boolean hasGroupBy = Lists.isNotEmpty(groupByItems);
		boolean hasHaving = Lists.isNotEmpty(havingItems);
		if (!hasGroupBy && hasHaving) {
			sb.append("\n");
			sb.append("group by null ");
		}
		if (hasGroupBy) {
			sb.append("\n");
			sb.append("group by ");
			for (int i = 0; i < groupByItems.size(); i++) {
				if (i != 0) {
					sb.append(",");
				}
				buildGroupBy(groupByItems.get(i), sb);
			}
		}

		if (hasHaving) {
			sb.append("\n");
			sb.append("having ");
			for (int i = 0; i < havingItems.size(); i++) {
				if (i != 0) {
					sb.append("\n");
					sb.append("and");
				}
				buildHaving(havingItems.get(i), sb, params);
			}
		}

		if (orderByItems != null && !orderByItems.isEmpty()) {
			sb.append("\n");
			sb.append("order by ");
			for (int i = 0; i < orderByItems.size(); i++) {
				if (i != 0) {
					sb.append(",");
					sb.append("\n");
				}
				buildOrderBy(orderByItems.get(i), sb);
			}
		}
		
		if (limit != null) {
			sb.append("\n");
			buildLimit(limit, sb);
		}

		String sql = sb.toString();
		sqlCommand.setSql(sql);
		sqlCommand.setParams(params);
	}

	private void buildSelect(SelectItem item, StringBuilder sb) {
		String name = item.getName();
		String aggregator = item.getAggregator();
		String alias = item.getAlias();
		if (Strings.isNotEmpty(aggregator)) {
			sb.append(aggregator);
			sb.append("(");
			sb.append(name);
			sb.append(")");
		} else {
			sb.append(name);
		}

		if (Strings.isEmpty(alias)) {
			alias = name;
		}
		sb.append(" as ");
		sb.append(name);
	}

	private void buildWhere(WhereItem item, StringBuilder sb, List<Object> params) {
		String name = item.getName();
		String operator = item.getOperator();
		List<Object> values = item.getValues();
		if (operator == null) {
			boolean ifNull = item.isIfNull();
			boolean ifNotNull = item.isIfNotNull();
			if (ifNull) {
				sb.append(" is null ");
			} else if (ifNotNull) {
				sb.append(" is not null ");
			}
			return;
		}

		if (values == null || values.isEmpty()) {
			return;
		}

		int size = values.size();
		if (size == 1) {
			sb.append(name);
			sb.append(operator);
			sb.append(" ? ");
			params.add(values.get(0));
			return;
		}

		String logic = " or ";
		if ("!=".equals(operator) || "<>".equals(operator)) {
			logic = " and ";
		}
		sb.append(" (");
		for (int i = 0; i < size; i++) {
			Object value = values.get(i);
			if (i != 0) {
				sb.append(logic);
			}
			sb.append(name);
			sb.append(operator);
			sb.append("?");
			params.add(value);
		}
		sb.append(") ");
	}

	private void buildGroupBy(GroupByItem item, StringBuilder sb) {
		String name = item.getName();
		sb.append(name);
	}

	private void buildHaving(HavingItem item, StringBuilder sb, List<Object> params) {
		List<Object> values = item.getValues();
		if (values == null || values.isEmpty()) {
			return;
		}
		String name = item.getName();
		String aggregator = item.getAggregator();
		String operator = item.getOperator();
		String tName = name;
		if (Strings.isNotEmpty(aggregator)) {
			tName = String.format("%s(%s)", aggregator, name);
		}
		int size = values.size();
		if (size == 1) {
			sb.append(tName);
			sb.append(aggregator);
			sb.append(" ? ");
			params.add(values.get(0));
		} else {
			String logic = " or ";
			if ("!=".equals(operator) || "<>".equals(operator)) {
				logic = " and ";
			}
			sb.append(" (");
			for (int i = 0; i < size; i++) {
				Object value = values.get(i);
				if (i != 0) {
					sb.append(logic);
				}
				sb.append(name);
				sb.append(" ? ");
				params.add(value);
			}
			sb.append(") ");
		}
	}

	private void buildOrderBy(OrderByItem item, StringBuilder sb) {
		String name = item.getName();
		String order = item.getOrder();
		if (name == null || name.isEmpty() || order == null || order.isEmpty()) {
			return;
		}
		boolean has = false;
		if ("desc".equals(order) || "asc".equals(order)) {
			sb.append(name);
			sb.append(" ");
			sb.append(order);
			has = true;
		} else {
			List<Object> values = item.getValues();
			if (values != null && !values.isEmpty()) {
				sb.append("case ");
				sb.append(name);
				int n = 0;
				for (Object value: values) {
					sb.append(" when ");
					if (value instanceof String) {
						sb.append("'");
						sb.append(value);
						sb.append("'");
					} else {
						sb.append(value);
					}
					sb.append(" then ");
					sb.append(n++);
				}
				sb.append(" else 99999");
				sb.append(" end asc");
				has = true;
			}
		}
		if (has) {
			if (item.isNullFirst()) {
				sb.append(" null first ");
			} else {
			}
		}
	}
	
	private void buildLimit(Limit limit, StringBuilder sb) {
		int start = limit.getStart();
		int size = limit.getSize();
		sb.append(String.format("limit %d,%d", start, size));
	}

}
