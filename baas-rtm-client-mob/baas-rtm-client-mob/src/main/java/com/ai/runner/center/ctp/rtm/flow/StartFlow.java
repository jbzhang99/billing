package com.ai.runner.center.ctp.rtm.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StartFlow {
private static Logger logger = LoggerFactory.getLogger(StartFlow.class);
public static void main(String[] args) throws Exception {
	logger.debug("开始加载spring配置文件");
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-properties.xml");
	logger.debug("配置文件加载成功");
	RtmFlow flow = (RtmFlow)context.getBean("rtmFlow");
	logger.debug("拿到流程执行类");
	try {
		flow.startRun();
	} catch (Exception e) {
		logger.error("流程执行失败");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	logger.debug("流程执行成功");
}
}
