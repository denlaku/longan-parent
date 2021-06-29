package com.denlaku.longan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.qo.CustomApiQuery;
import com.denlaku.longan.service.CustomApiInfoService;
import com.denlaku.longan.vo.CustomApi;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/custom-api-info")
public class CustomApiInfoController {

	@Autowired
	private CustomApiInfoService customApiInfoService;

	@PostMapping("/add")
	public Return<String> add(@RequestBody CustomApi customApi) {
		String nextId = customApiInfoService.nextId();
		customApi.setId(nextId);
		customApiInfoService.add(customApi);
		return Resp.success(nextId);
	}

	@PostMapping("/update")
	public Return<Void> update(@RequestBody CustomApi customApi) {
		customApiInfoService.update(customApi);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> delete(String id) {
		customApiInfoService.delete(id);
		return Resp.success();
	}

	@GetMapping("/get")
	public Return<CustomApi> get(String id) {
		 CustomApi customApi = customApiInfoService.get(id);
		return Resp.success(customApi);
	}

	@VarBy
	@PostMapping("/page-list")
	public Return<List<CustomApi>> list(CustomApiQuery query, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<CustomApi> list = customApiInfoService.list(query);
		return Resp.success(list);
	}

}
