package com.denlaku.longan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.denlaku.longan.qo.UserQuery;
import com.denlaku.longan.vo.User;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO
 * @author tianx
 */
@Repository
public interface UserDao {

	/**
	 * 根据ID查询用户
	 * @param id 用户ID
	 * @return
	 */
	User get(User id);

	/**
	 * 根据参数查询用户
	 *
	 * @param userQuery 参数查询
	 * @return
	 */
	List<User> list(UserQuery userQuery);

	/**
	 * 根据参数查询用户，并map形式返回：id -> user
	 * @param userQuery 参数查询
	 * @return map形式的用户数据
	 */
	@MapKey("id")
	Map<String, User> listMap(UserQuery userQuery);

	/**
	 * 新增
	 * @param user 用户
	 * @return 新增记录条数
	 */
	int add(User user);

	/**
	 * 更新用户
	 * @param user 用户
	 * @return 更新记录条数
	 */
	int update(User user);

	/**
	 * 删除用户
	 * @param id 用户ID
	 * @return 删除记录条数
	 */
	int delete(String id);

}
