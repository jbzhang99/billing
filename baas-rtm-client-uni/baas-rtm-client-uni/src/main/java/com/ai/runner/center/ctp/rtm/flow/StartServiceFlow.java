package com.ai.runner.center.ctp.rtm.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.runner.center.ctp.rtm.core.executor.DeliverHandler;
import com.ai.runner.center.ctp.rtm.core.executor.WrapperExecutor;
import com.ai.runner.center.ctp.rtm.core.util.DataSourceUtil;
import com.ai.runner.center.ctp.rtm.core.util.GenerateAPNUtil;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;


public class StartServiceFlow {
private static Logger logger = LoggerFactory.getLogger(StartServiceFlow.class);
public static void main(String[] args) throws Exception {
	logger.debug("开始加载spring配置文件");
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-properties.xml");
	logger.debug("配置文件加载成功");
	RtmFlow flow = (RtmFlow)context.getBean("rtmFlow");
	logger.debug("拿到流程执行类");
	try {
		GenerateAPNUtil.load("apn-source.properties");
		PropertiesUtil.load("ctp-rtm.properties");
		logger.debug("[读取配置文件成功!]");
		DataSourceUtil.load();//创建数据库连接池
		logger.debug("[加载数据源完成!]");
		
		WrapperExecutor.create();//创建线程池
		
		DeliverHandler deliverHandler = new DeliverHandler();
		deliverHandler.start();
		logger.debug("[文件处理器启动成功!]");
		flow.startRun();
	} catch (Exception e) {
		logger.error("流程执行失败",e);
	}
	logger.debug("流程执行成功");
}
}
