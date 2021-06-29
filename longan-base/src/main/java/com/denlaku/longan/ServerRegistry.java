package com.denlaku.longan;

import com.denlaku.longan.dao.ServerDao;
import com.denlaku.longan.util.ServerUtil;
import com.denlaku.longan.vo.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务器注册
 * @author tianx
 */
@Service
public class ServerRegistry implements SmartInitializingSingleton, BeanFactoryAware {

	private final Logger logger = LoggerFactory.getLogger(ServerRegistry.class);

	private final int tryTimes = 5;
	private BeanFactory beanFactory;

	@Autowired
	private ServerDao serverDao;

	@Transactional(rollbackFor = Exception.class)
	public void registry() {
		Server server = getServer();
		if (server != null) {
			return;
		}
		server = new Server();
		server.setIp(ServerUtil.getIp());
		server.setPlatform(ServerUtil.getPlatform());
		boolean success = false;
		for (int i = 1; i <= tryTimes; i++) {
			success = doRegistry(server, i);
			if (success) {
				break;
			}
		}
		if (!success) {
			throw new RuntimeException("服务器注册失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void update() {
		List<String> ips = ServerUtil.getIps();
		for (String ip : ips) {
			int update = serverDao.update(ip);
			if (update != 0) {
				break;
			}
		}
	}

	private boolean doRegistry(Server server, int times) {
		if (getServer() != null) {
			return true;
		}
		try {
			serverDao.registry(server);
			return true;
		} catch (Exception e) {
			String msg = String.format("第%d次注册失败", times + 1);
			logger.error(msg, e);
			return false;
		}
	}

	public Server getServer() {
		List<String> ips = ServerUtil.getIps();
		for (String ip : ips) {
			Server server = serverDao.get(ip);
			if (server != null) {
				return server;
			}
		}
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.registry();
//		ServerRegistry serverRegistry = this;
//		Thread t = new Thread(() -> {
//			new Timer("server-registry-timer").schedule(new TimerTask() {
//				@Override
//				public void run() {
//					serverRegistry.update();
//				}
//			}, 1000, 1000);
//		});
//		t.setName("server-registry-thread");
//		t.setDaemon(true);
//		t.start();
	}
}
