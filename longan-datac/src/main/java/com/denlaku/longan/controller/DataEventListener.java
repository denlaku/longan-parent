package com.denlaku.longan.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.denlaku.longan.datac.JdbcType;
import com.denlaku.longan.vo.ExcelCell;

/**
 * @author tianx
 */
public class DataEventListener extends AnalysisEventListener<Map<Integer, String>> {

	private static String VARCHAR = JdbcType.VARCHAR.name();
	private static String DECIMAL = JdbcType.DECIMAL.name();
	private static String BIGINT = JdbcType.BIGINT.name();
	private static String DATE = JdbcType.DATE.name();

	private Map<Integer, String> headMap;
	private Map<String, String> typeMap = new HashMap<>();
	private List<List<ExcelCell>> rows = new ArrayList<>();

	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		super.invokeHeadMap(headMap, context);
		this.headMap = headMap;
	}

	@Override
	public void invoke(Map<Integer, String> data, AnalysisContext context) {
		ReadRowHolder readRowHolder = context.readRowHolder();
		Integer rowIndex = readRowHolder.getRowIndex();
		List<ExcelCell> row = new ArrayList<>();
		data.forEach((col, text) -> {
			String head = headMap.get(col);
			ExcelCell cell = new ExcelCell(rowIndex, col, head, text);
			detach(cell);
			row.add(cell);
		});
		rows.add(row);
	}

	private void detach(ExcelCell cell) {
		String text = cell.getText();
		String head = cell.getHead();
		if (text == null || "".equals(text)) {
			return;
		}
		String oldType = typeMap.get(head);
		if (VARCHAR.equals(oldType)) {
			return;
		}
		String type = VARCHAR;
		Object value = null;
		if (oldType == null) {
			if ((value = toLong(text)) != null) {
				typeMap.put(head, BIGINT);
				type = BIGINT;
			} else if ((value = toDecimal(text)) != null) {
				typeMap.put(head, DECIMAL);
				type = DECIMAL;
			} else if ((value = toDate(text)) != null) {
				typeMap.put(head, DATE);
				type = DATE;
			} else {
				typeMap.put(head, VARCHAR);
			}
		} else {
			if ((value = toLong(text)) != null) {
				type = BIGINT;
				if (DECIMAL.equals(oldType)) {
					typeMap.put(head, DECIMAL);
				} else if (VARCHAR.equals(oldType) || DATE.equals(oldType)) {
					typeMap.put(head, VARCHAR);
				}
			} else if ((value = toDecimal(text)) != null) {
				type = DECIMAL;
				if (VARCHAR.equals(oldType) || DATE.equals(oldType)) {
					typeMap.put(head, VARCHAR);
				}
			} else if ((value = toDate(text)) != null) {
				type = DATE;
				if (!DATE.equals(oldType)) {
					typeMap.put(head, VARCHAR);
				}
			}
		}
		cell.setType(type);
		cell.setValue(value);
	}

	private Long toLong(String text) {
		try {
			return Long.parseLong(text);
		} catch (Exception e) {
			return null;
		}
	}

	private Double toDecimal(String text) {
		try {
			if (!text.contains(".")) {
				return null;
			}
			return Double.parseDouble(text);
		} catch (Exception e) {
			return null;
		}
	}

	private Date toDate(String text) {
		try {
			int length = text.length();
			boolean digitMatch = Character.isDigit(text.charAt(0)) && //
					(Character.isDigit(text.charAt(length - 1)) || Character.isDigit(text.charAt(length - 2)));
			if (digitMatch) {
				boolean isDate = false;
				if (length > 6 && length < 11) {
					String regex1 = "\\d{4}(\\-|\\/|\\.)\\d{1,2}((\\-|\\/|\\.)\\d{1,2})?";
					String regex2 = "\\d{2,4}年\\d{1,2}月(\\d{1,2}日)?";
					if (text.matches(regex1) || text.matches(regex2)) {
						isDate = true;
					}
				} else if (length >= 11 && length <= 19) {
					String regex = "\\d{4}(\\-|\\/)\\d{1,2}(\\-|\\/)\\d{1,2}(\\s|T)\\d{1,2}:\\d{1,2}(:\\d{1,2})?";
					if (text.matches(regex)) {
						isDate = true;
					}
				}
				if (isDate) {
					String[] tokens = { "y", "M", "d", "H", "m", "s" };
					StringBuilder sb = new StringBuilder();
					char[] chars = text.toCharArray();
					int i = 0;
					for (Character ch : chars) {
						if (Character.isDigit(ch)) {
							sb.append(tokens[i]);
						} else {
							sb.append(ch);
							i++;
						}
					}
					SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
					return sdf.parse(text);
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {

	}

	public List<String> getHeads() {
		List<String> heads = new ArrayList<>();
		headMap.forEach((k, v) -> {
			if (typeMap.containsKey(v)) {
				heads.add(v);
			}
		});
		return heads;
	}

	public List<List<ExcelCell>> getRows() {
		return rows;
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

}
