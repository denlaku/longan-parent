package com.denlaku.longan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.denlaku.longan.qo.BizTagQuery;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.excel.EasyExcel;
import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.datac.DataType;
import com.denlaku.longan.datac.JdbcType;
import com.denlaku.longan.service.DataSetFieldService;
import com.denlaku.longan.service.DataSetService;
import com.denlaku.longan.service.ExcelImportService;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/data-set")
public class DataSetController {

	@Autowired
	private DataSetService dataSetService;
	@Autowired
	private DataSetFieldService dataSetFieldService;
	@Autowired
	private ExcelImportService excelImportService;

	@VarOp
	@PostMapping("/add")
	public Return<Void> add(@RequestBody DataSet dataSet) {
		String nextId = dataSetService.nextId();
		dataSet.setId(nextId);
		dataSetService.add(dataSet);
		return Resp.success();
	}
	
	@VarOp
	@PostMapping("/excel")
	public Return<Void> excel(MultipartRequest request) throws IOException, SQLException {
		MultipartFile file = request.getFile("file");
		if (file == null) {
			return Resp.error("文件不能为空");
		}
		String originalFilename = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		DataEventListener dataEventListener = new DataEventListener();
		EasyExcel.read(inputStream, null, dataEventListener).sheet().doRead();
		List<String> heads = dataEventListener.getHeads();
		Map<String, String> typeMap = dataEventListener.getTypeMap();
		List<List<ExcelCell>> rows = dataEventListener.getRows();
		if (heads.isEmpty()) {
			return Resp.error("excel文件需存在有效的列");
		}
		String tableName = excelImportService.create(heads, typeMap);
		excelImportService.intsert(heads, typeMap, tableName, rows);
		List<DataSetField> fields = buildFields(heads, typeMap);
		DataSet dataSet = new DataSet();
		String nextId = dataSetService.nextId();
		dataSet.setId(nextId);
		dataSet.setName(originalFilename);
		dataSet.setType("2");
		dataSet.setDataSourceId("0");
		dataSet.setCommand(tableName);
		dataSet.setFields(fields);
		dataSetService.add(dataSet);
		return Resp.success();
	}
	
	private List<DataSetField> buildFields(List<String> heads, Map<String, String> typeMap) {
		List<DataSetField> list = new ArrayList<>();
		for (String head: heads) {
			String jdbcType = typeMap.get(head);
			String dataType = JdbcType.getDataType(jdbcType);
			DataSetField field = new DataSetField();
			field.setName(head);
			field.setAlias(head);
			field.setJdbcType(jdbcType);
			field.setDataType(dataType);
			field.setType(DataType.number.equals(dataType) ? "2": "1");
			list.add(field);
		}
		return list;
	}
	
	@VarOp
	@PostMapping("/update")
	public Return<Void> update(@RequestBody DataSet dataSet) {
		dataSetService.update(dataSet);
		return Resp.success();
	}
	
	@PostMapping("/delete")
	public Return<Void> delete(String id) {
		dataSetService.delete(id);
		return Resp.success();
	}

	@PostMapping("/page-list")
	@VarBy
	public Return<List<DataSet>> list(DataSet dataSet, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<DataSet> list = dataSetService.list(dataSet);
		return Resp.success(list);
	}
	
	@PostMapping("/list")
	public Return<List<DataSet>> list(DataSet dataSet) {
		List<DataSet> list = dataSetService.list(dataSet);
		if (Lists.isNotEmpty(list)) {
			list.forEach(ds -> {
				List<DataSetField> fields = dataSetFieldService.list(ds.getId());
				ds.setFields(fields);
			});
		}
		return Resp.success(list);
	}

	@GetMapping("/get")
	public Return<DataSet> get(String id) {
		DataSet dataSet = dataSetService.getCache(id);
		return Resp.success(dataSet);
	}

	@PostMapping("/list-meta-data")
	public Return<List<MetaData>> listMetaData(@RequestBody DataSet dataSet) {
		List<MetaData> listMetaData = dataSetService.listMetaData(dataSet);
		return Resp.success(listMetaData);
	}

	@RequestMapping("/add-tag")
	public int addTag(BizTag tag) {
		return dataSetService.addTag(tag);
	}

	@RequestMapping("/delete-tag")
	public int deleteTag(BizTagQuery query) {
		return dataSetService.deleteTag(query);
	}

	@RequestMapping("/list-tag")
	public List<BizTag> listTag(BizTagQuery query) {
		return dataSetService.listTag(query);
	}

}
