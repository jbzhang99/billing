package com.ai.runner.center.ctp.rtm.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.executor.DeliverHandler;
import com.ai.runner.center.ctp.rtm.core.executor.ProcessHandler;
import com.ai.runner.center.ctp.rtm.core.executor.WrapperExecutor;
import com.ai.runner.center.ctp.rtm.core.producer.ProducerProxy;
import com.ai.runner.center.ctp.rtm.core.util.DataSourceUtil;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class RtmDubboStartup {
	
	private static Logger logger = LoggerFactory.getLogger(RtmDubboStartup.class);
	
	public void start(){
		logger.debug("[通过Dubbo容器开始加载Rtm资源信息...]");
		try{
			runTool();
		}catch(Exception e){
			logger.error("context", e);
			logger.debug("[资源加载失败...]");
		}
		logger.debug("[资源加载成功...]");
	}
	
	private void runTool() throws Exception{
		PropertiesUtil.load("ctp-rtm.properties");
		logger.debug("[读取配置文件成功!]");
//		DataSourceUtil.load();
//		logger.debug("[加载数据源完成!]");
		WrapperExecutor wrapper =new WrapperExecutor();
		wrapper.create();
		ProcessHandler processHandler = new ProcessHandler();
		processHandler.start();
		logger.debug("[任务处理器启动成功!]");
		
		DeliverHandler deliverHandler = new DeliverHandler();
		deliverHandler.start();
		logger.debug("[消息分发器启动成功!]");
		ProducerProxy producer=new ProducerProxy();
		producer.getInstance();
	}
	
	
	
}
