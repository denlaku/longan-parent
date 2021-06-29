package com.denlaku.longan.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.denlaku.longan.ServerRegistry;
import com.denlaku.longan.SnowFlake;
import com.denlaku.longan.vo.Server;

/**
 * @author tianx
 */
@Service
public class SnowFlakeService implements ApplicationContextAware {

	private static ApplicationContext ctt;
	private static SnowFlakeService snowFlakeService;
	
	private Map<String, SnowFlake> cache = new ConcurrentHashMap<>();

	@Autowired
	private ServerRegistry serverRegistry;

	private Server server;

	private String next(String biz) {
		SnowFlake snowFlake = getSnowFlake(biz);
		return lpad(Long.toHexString(snowFlake.nextId()), 16);
	}

	private String lpad(String str, int minLen) {
		int diff = minLen - str.length() ;
		if (diff <= 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < diff; i++) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}

	private SnowFlake getSnowFlake(String biz) {
		SnowFlake snowFlake = cache.get(biz);
		if (snowFlake == null) {
			init(biz);
		}
		return cache.get(biz);
	}

	private synchronized SnowFlake init(String biz) {
		SnowFlake snowFlake = cache.get(biz);
		if (snowFlake != null) {
			return snowFlake;
		}
		if (server == null) {
			server = serverRegistry.getServer();
		}
		int machineId = server.getMachineId();
		int high = machineId >> 5;
		int low = machineId & 31;
		snowFlake = new SnowFlake(high, low);
		cache.put(biz, snowFlake);
		return snowFlake;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SnowFlakeService.ctt = applicationContext;
	}
	
	public static String nextId(String biz) {
		if (snowFlakeService == null) {
			snowFlakeService = ctt.getBean(SnowFlakeService.class);
		}
		return snowFlakeService.next(biz);
	}

}
