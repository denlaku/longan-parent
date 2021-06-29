package com.denlaku.longan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页Controller
 *
 * @author tianx
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "/index.html";
	}
	
	@RequestMapping("/v-*")
	public String indexView() {
		return "/index.html";
	}
	
	@RequestMapping(value = {"/mobile/index", "/mobile/"})
	public String indexMobile() {
		return "/mobile/index.html";
	}
	
	@RequestMapping("/mobile/v-*")
	public String indexMobileView() {
		return "/mobile/index.html";
	}
	
}
