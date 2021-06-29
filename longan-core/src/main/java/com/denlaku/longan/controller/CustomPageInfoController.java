package com.denlaku.longan.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.denlaku.longan.Current;
import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.config.ConfigProperties;
import com.denlaku.longan.service.CustomPageService;
import com.denlaku.longan.util.FileUtil;
import com.denlaku.longan.vo.CustomPage;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Slf4j
@RestController
@RequestMapping("/api/custom-page-info")
public class CustomPageInfoController {

	@Autowired
	private CustomPageService customPageService;
	
	@Autowired
	private ConfigProperties configProperties;

	@PostMapping("/add")
	public Return<Object> add(@RequestBody CustomPage customPage) {
		String nextId = customPageService.nextId();
		customPage.setId(nextId);
		customPageService.add(customPage);
		return Resp.success();
	}

	@PostMapping("/update")
	public Return<Object> update(@RequestBody CustomPage customPage) {
		customPageService.update(customPage);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Object> delete(String id) {
		customPageService.delete(id);
		return Resp.success();
	}
	
	@PostMapping("/upload")
	public Return<Void> upload(MultipartHttpServletRequest request) {
		try {
			Iterator<String> fileNames = request.getFileNames();
			if (!fileNames.hasNext()) {
				return Resp.error("没有选择文件！");
			}
			String id = request.getParameter("id");
			CustomPage customPage = customPageService.get(id);
			if (customPage == null) {
				return Resp.error("页面不存在！");
			}
			String fileName = fileNames.next();
			MultipartFile mFile = request.getFile(fileName);
			Path path = Paths.get(configProperties.getDir(), "custom-page", id);
			File file = path.toFile();
			if (file.exists()) {
//				file.delete();
				FileUtil.delete(file);
			}
			if (!file.exists()) {
				file.mkdirs();
			}
			ZipInputStream zipInputStream = new ZipInputStream(mFile.getInputStream());
			ZipUtil.unzip(zipInputStream, file);
			return Resp.message("上传成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Resp.error("上传失败");
	}

	@VarBy
	@PostMapping("/page-list")
	public Return<List<CustomPage>> pageList(@RequestBody CustomPage customPage, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		String tenantId = Current.getTenantId();
		customPage.setTenantId(tenantId);
		List<CustomPage> list = customPageService.list(customPage);
		return Resp.success(list);
	}

	@GetMapping("/get")
	public Return<CustomPage> get(String id) {
		CustomPage customPage = customPageService.get(id);
		return Resp.success(customPage);
	}

}
