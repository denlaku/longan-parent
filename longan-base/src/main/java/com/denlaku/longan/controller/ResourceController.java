package com.denlaku.longan.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.config.ConfigProperties;
import com.denlaku.longan.service.ResourceService;
import com.denlaku.longan.util.DateUtil;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.vo.Resource;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/resource")
@Slf4j
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ConfigProperties configProperties;

	@RequestMapping("/upload")
	public Return<Resource> upload(MultipartHttpServletRequest request) {
		Iterator<String> fileNames = request.getFileNames();
		if (!fileNames.hasNext()) {
			return Resp.error("没有选择文件！");
		}
		String fileName = fileNames.next();
		MultipartFile mFile = request.getFile(fileName);
		String id = resourceService.nextId();
		Resource resource = newResource(mFile, id);
		String fileDir = getFileDir(resource.getPath());
		File file = new File(fileDir + "/" + id);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		try {
			mFile.transferTo(file);
			resourceService.add(resource);
		} catch (IllegalStateException | IOException e) {
			log.error("upload resource error", e);
			return Resp.error("上传文件失败");
		}
		return Resp.success(resource);
	}

	private Resource newResource(MultipartFile mFile, String id) {
		Resource resource = new Resource();
		resource.setId(id);
		resource.setName(mFile.getOriginalFilename());
		resource.setContentType(mFile.getContentType());
		resource.setSize(mFile.getSize());
		resource.setPath(getPath());
		return resource;
	}

	private String getFileDir(String path) {
		String fileDir = configProperties.getDir();
		if (!fileDir.endsWith("/")) {
			fileDir += "/";
		}
		return fileDir + path;
	}

	private String getPath() {
		return "upload/" + DateUtil.format(new Date(), "yyyy/MM/dd");
	}

	@RequestMapping("/download/{id}")
	public void download(@PathVariable("id") String id, HttpServletResponse response) {
		Resource resource = resourceService.get(id);
		if (resource != null) {
			String fileDir = getFileDir(resource.getPath());
			File file = new File(fileDir, id);
			if (file.exists()) {
				try {
					String name = resource.getName();
					response.setContentType(resource.getContentType());
					response.setContentLengthLong(file.length());
					response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=" + URLEncoder.encode(name, "UTF-8"));
					Files.copy(file.toPath(), response.getOutputStream());
				} catch (IOException e) {
					log.error("download error", e);
				}
			} else {
				response.setStatus(404);
			}
		}
	}

	@RequestMapping("/image/{id}")
	public void image(@PathVariable("id") String id, HttpServletResponse response) {
		Resource resource = resourceService.get(id);
		if (resource != null) {
			String path = resource.getPath();
			String fileDir = getFileDir(path);
			File file = new File(fileDir, id);
			if (file.exists()) {
				try {
					response.setContentType(resource.getContentType());
					response.setContentLengthLong(file.length());
					Files.copy(file.toPath(), response.getOutputStream());
				} catch (IOException e) {
					log.error("get image error", e);
				}
			} else {
				response.setStatus(404);
			}
		}
	}

	@RequestMapping("/delete")
	public Return<Void> delete(String id) {
		resourceService.delete(id);
		return Resp.success();
	}

	@VarBy
	@PostMapping("/page-list")
	public Return<List<Resource>> pageList(@RequestBody Resource dashboard, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<Resource> list = resourceService.list(dashboard);
		return Resp.success(list);
	}
}
