package com.denlaku.longan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.service.ComponentService;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/component")
public class ComponentController {

	@Autowired
	private ComponentService componentService;

	@PostMapping("/next")
	public Return<String> next() {
		String next = componentService.nextId();
		return Resp.success(next);
	}

}
