package com.denlaku.longan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.excel.EasyExcel;
import com.denlaku.longan.qo.DataPowerNodeQuery;
import com.denlaku.longan.service.DataPowerNodeEventListener;
import com.denlaku.longan.service.DataPowerNodeService;
import com.denlaku.longan.vo.DataPowerNode;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
@RestController
@RequestMapping("/api/data-power-node")
public class DataPowerNodeController {

	@Autowired
	private DataPowerNodeService dataPowerNodeService;

	@PostMapping("/add")
	public Return<Void> add(@RequestBody DataPowerNode dataPowerNode) {
		String nextId = dataPowerNodeService.nextId();
		dataPowerNode.setId(nextId);
		dataPowerNodeService.batchAdd(Arrays.asList(dataPowerNode));
		return Resp.success();
	}

	@PostMapping("/update")
	public Return<Void> update(@RequestBody DataPowerNode dataPowerNode) {
		dataPowerNodeService.update(dataPowerNode);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> delete(DataPowerNodeQuery query) {
		dataPowerNodeService.delete(query);
		return Resp.success();
	}

	@GetMapping("/get")
	public Return<DataPowerNode> get(String id) {
		DataPowerNodeQuery query = DataPowerNodeQuery.of(id);
		DataPowerNode dataPowerSpace = dataPowerNodeService.get(query);
		return Resp.success(dataPowerSpace);
	}
	
	@PostMapping("/upload")
	public Return<Void> upload(String spaceId, MultipartRequest request) {
		MultipartFile file = request.getFile("file");
		try {
			InputStream inputStream = file.getInputStream();
			DataPowerNodeEventListener listener = new DataPowerNodeEventListener();
			EasyExcel.read(inputStream, DataPowerNode.class, listener).sheet().doRead();
			List<DataPowerNode> dataPowerNodes = listener.getList();
			if (dataPowerNodes != null) {
				dataPowerNodes.forEach(dataPowerNode -> {
					String nextId = dataPowerNodeService.nextId();
					dataPowerNode.setId(nextId);
					dataPowerNode.setSpaceId(spaceId);
				});
			}
			dataPowerNodeService.batchAdd(dataPowerNodes);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return Resp.success();
	}

	@GetMapping("/list")
	public Return<List<DataPowerNode>> list(DataPowerNodeQuery query) {
		List<DataPowerNode> list = dataPowerNodeService.list(query);
		return Resp.success(list);
	}
	
	@GetMapping("/types")
	public Return<List<String>> types(DataPowerNodeQuery query) {
		List<String> types = new ArrayList<>();
		List<DataPowerNode> nodes = dataPowerNodeService.list(query);
		if (CollUtil.isNotEmpty(nodes)) {
			nodes.forEach(node -> {
				String type = node.getType();
				if (!types.contains(type)) {
					types.add(type);
				}
			});
		}
		return Resp.success(types);
	}

}
