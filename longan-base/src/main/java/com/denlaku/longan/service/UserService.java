package com.denlaku.longan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.UserDao;
import com.denlaku.longan.qo.UserQuery;
import com.denlaku.longan.vo.User;

/**
 * @author tianx
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getById(String id) {
		User user = new User();
		user.setId(id);
		return userDao.get(user);
	}

	public User getByAccount(String account) {
		User user = new User();
		user.setAccount(account);
		return userDao.get(user);
	}

	public List<User> list(UserQuery userQuery) {
		return userDao.list(userQuery);
	}
	
	public Map<String, User> listMap(UserQuery userQuery) {
		return userDao.listMap(userQuery);
	}

	public void disabled(String id) {
		User user = new User();
		// 2-禁用
		user.setStatus("2");
		userDao.update(user);
	}

	public void enabled(String id) {
		User user = new User();
		// 1-启用
		user.setStatus("1");
		userDao.update(user);
	}

	public int update(User user) {
		return userDao.update(user);
	}

	public int delete(String id) {
		return userDao.delete(id);
	}

	public int add(User user) {
		return userDao.add(user);
	}

}
