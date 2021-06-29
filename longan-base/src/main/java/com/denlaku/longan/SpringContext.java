package com.denlaku.longan;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring上下文
 * @author tianx
 */
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext ctt;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctt = applicationContext;
	}

	public static <T> T getBean(Class<T> cls) {
		return ctt.getBean(cls);
	}

}
