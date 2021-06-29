package com.denlaku.longan.controller;

import java.util.List;
import java.util.Map;

import com.denlaku.longan.util.Lists;
import com.denlaku.longan.vo.CustomApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.service.CustomApiService;
import com.denlaku.longan.vo.CustomApi;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/custom-api")
public class CustomApiController {

	@Autowired
	private CustomApiService customApiService;

	@PostMapping("/fetch")
	public Return<List<Map<String, Object>>> fetch(@RequestBody CustomApi customApi) {
		CustomApiData apiData = customApiService.fetch(customApi);
		return Resp.success(apiData.getRows());
	}
	
	@PostMapping("/fetches")
	public Return<Map<String, List<Map<String, Object>>>> fetches(@RequestBody List<CustomApi> customApis) {
		List<CustomApiData> list = customApiService.fetches(customApis);
		Map<String, List<Map<String, Object>>> result = Lists.toMap(list, CustomApiData::getId, CustomApiData::getRows);
		return Resp.success(result);
	}


}
