package com.ai.runner.center.ctp.rtm.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {

	private static Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);
	private static ApplicationContextUtil instance = null;
	private ApplicationContext context;
	
	public static ApplicationContextUtil getInstance(){
		if(instance == null){
			instance = new ApplicationContextUtil();
			instance.loadResource();
		}
		return instance;
	}
	
	private void loadResource(){
		context = new ClassPathXmlApplicationContext("app-context.xml");
		logger.debug("ApplicationContext 已加载完成!");
	}
	
	public Object getBean(String beanName) {
		if (beanName != null && !"".equals(beanName)) {
			return context.getBean(beanName);
		}
		return null;
	}
	
}
