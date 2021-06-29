package com.denlaku.longan.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.anno.VarBy;
import com.denlaku.longan.qo.DataPowerSpaceQuery;
import com.denlaku.longan.service.DataPowerSpaceService;
import com.denlaku.longan.util.IOUtil;
import com.denlaku.longan.vo.DataPowerSpace;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/data-power-space")
@Slf4j
public class DataPowerSpaceController {

	@Autowired
	private DataPowerSpaceService dataPowerSpaceService;
	
	@GetMapping("/template")
	public void template(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
		    response.setContentType("multipart/form-data");
		    response.setHeader("Content-Disposition", "attachment;fileName=template.xlsx");
			ServletOutputStream outputStream = response.getOutputStream();
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template.xlsx");
	        OutputStream os = response.getOutputStream();
	        byte[] buf = new byte[4096];
	        int len;
	        while ((len = inputStream.read(buf)) > 0) {
	            os.write(buf, 0, len);
	        }
	        outputStream.flush();
	        IOUtil.close(inputStream);
	        IOUtil.close(outputStream);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@PostMapping("/add")
	public Return<Void> add(@RequestBody DataPowerSpace dataPowerSpace) {
		String nextId = dataPowerSpaceService.nextId();
		dataPowerSpace.setId(nextId);
		dataPowerSpaceService.add(dataPowerSpace);
		return Resp.success();
	}

	@PostMapping("/update")
	public Return<Void> update(@RequestBody DataPowerSpace dataPowerSpace) {
		dataPowerSpaceService.update(dataPowerSpace);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> delete(DataPowerSpaceQuery query) {
		dataPowerSpaceService.delete(query);
		return Resp.success();
	}

	@GetMapping("/get")
	public Return<DataPowerSpace> get(String id) {
		DataPowerSpaceQuery query = DataPowerSpaceQuery.of(id);
		DataPowerSpace dataPowerSpace = dataPowerSpaceService.get(query);
		return Resp.success(dataPowerSpace);
	}
	
	@GetMapping("/list")
	public Return<List<DataPowerSpace>> list(DataPowerSpaceQuery query) {
		List<DataPowerSpace> list = dataPowerSpaceService.list(query);
		return Resp.success(list);
	}

	@VarBy
	@PostMapping("/page-list")
	public Return<List<DataPowerSpace>> pageList(@RequestBody DataPowerSpaceQuery query, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<DataPowerSpace> list = dataPowerSpaceService.list(query);
		return Resp.success(list);
	}
	
}
