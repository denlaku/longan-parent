package com.denlaku.longan.dao;

import com.denlaku.longan.vo.Server;
import org.springframework.stereotype.Repository;

/**
 * 服务器信息
 * @author tianx
 */
@Repository
public interface ServerDao {

	/**
	 * 注册服务器信息
	 * @param server 服务器信息
	 * @return 新增记录数
	 */
	int registry(Server server);

	/**
	 * 根据ip获取服务器信息
	 * @param ip IP地址
	 * @return 服务器
	 */
	Server get(String ip);

	/**
	 * 修改服务器信息
	 * @param ip IP地址
	 * @return 修改记录数
	 */
	int update(String ip);
}
