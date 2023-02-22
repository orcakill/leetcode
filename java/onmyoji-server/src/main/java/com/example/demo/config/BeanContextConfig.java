package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Classname BeanContext
 * @Description BeanContext
 * @Date 2023/2/23 2:38
 * @Created by orcakill
 */
@Component
public class BeanContextConfig implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext () {
		return applicationContext;
	}
	
	public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
		BeanContextConfig.applicationContext = applicationContext;
	}
	
	@SuppressWarnings ("unchecked")
	public static <T> T getBean (String name) throws BeansException {
		return (T) applicationContext.getBean (name);
	}
	
	public static <T> T getBean (Class<T> clz) throws BeansException {
		return applicationContext.getBean (clz);
	}
}


