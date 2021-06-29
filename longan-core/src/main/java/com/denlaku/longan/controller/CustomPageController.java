package com.denlaku.longan.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.denlaku.longan.service.CustomApiService;
import com.denlaku.longan.util.Lists;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.CustomApi;
import com.denlaku.longan.vo.CustomApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.denlaku.longan.config.ConfigProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
@Controller
@RequestMapping("/page")
@Api(tags = "定制页面")
public class CustomPageController {

	private static final String basePath = "custom-page";
	private static final String INDEX_HTML = "index.html";
	@Autowired
	private ConfigProperties configProperties;

	@Autowired
	private CustomApiService customApiService;

	@GetMapping(value= {"/{id}/index", "/{id}/"})
	public void source(@PathVariable("id") String id, HttpServletResponse response) {
		sendIndexFile(id, response);
	}

	@GetMapping("/{id}/**/*")
	public void source(@PathVariable("id") String id,
			HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		int index = uri.indexOf(id);
		String name = uri.substring(index + id.length() + 1);
		sendFile(id, name, response);
	}
	
	
	private void sendIndexFile(String id, HttpServletResponse response) {
		String dir = configProperties.getDir();
		Path path = Paths.get(dir, basePath, id, INDEX_HTML);
		File file = path.toFile();
		try {
			if (file.exists() && file.isFile()) {
				Files.copy(path, response.getOutputStream());
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void sendFile(String id, String name, HttpServletResponse response) {
		String dir = configProperties.getDir();
		Path path = Paths.get(dir, basePath, id, name);
		File file = path.toFile();
		try {
			if (file.exists() && file.isFile()) {
				Files.copy(path, response.getOutputStream());
			} else {
				sendIndexFile(id, response);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@ApiOperation("获取定制接口数据")
	@PostMapping("/fetches")
	@ResponseBody
	public Return<Map<String, List<Map<String, Object>>>> fetches(@RequestBody List<CustomApi> customApis) {
		List<CustomApiData> list = customApiService.fetches(customApis);
		Map<String, List<Map<String, Object>>> result = Lists.toMap(list, CustomApiData::getId, CustomApiData::getRows);
		return Resp.success(result);
	}

}
