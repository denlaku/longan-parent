package com.denlaku.longan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denlaku.longan.qo.UserQuery;
import com.denlaku.longan.service.UserService;
import com.denlaku.longan.vo.PageBy;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.User;
import com.github.pagehelper.PageHelper;

/**
 * @author tianx
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/page-list")
	public Return<List<User>> pageList(@RequestBody UserQuery userQuery, PageBy pageBy) {
		PageHelper.startPage(pageBy.getPage(), pageBy.getSize());
		List<User> list = userService.list(userQuery);
		return Resp.success(list);
	}

	@PostMapping("/status")
	public Return<Void> status(String id) {
		User user = userService.getById(id);
		if (user != null) {
			String status = user.getStatus();
			if ("1".equals(status)) {
				userService.disabled(id);
			} else if ("2".equals(status)) {
				userService.enabled(id);
			}
		}
		return Resp.success();
	}

	@PostMapping("/update")
	public Return<Void> update(@RequestBody User user) {
		userService.update(user);
		return Resp.success();
	}

	@PostMapping("/delete")
	public Return<Void> add(String id) {
		userService.delete(id);
		return Resp.success();
	}

	@PostMapping("/add")
	public Return<Void> add(@RequestBody User user) {
		userService.add(user);
		return Resp.success();
	}
}
