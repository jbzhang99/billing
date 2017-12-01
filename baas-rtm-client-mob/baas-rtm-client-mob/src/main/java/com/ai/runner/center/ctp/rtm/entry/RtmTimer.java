package com.ai.runner.center.ctp.rtm.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.runner.center.ctp.rtm.core.util.ApplicationContextUtil;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class RtmTimer {

	private static Logger logger = LoggerFactory.getLogger(RtmTimer.class);
	
	public static void startTimer(String[] args){
		logger.info("开始启动 timer 服务---------------------------");
		try{
			PropertiesUtil.load("ctp-rtm.properties");
//			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:app-context.xml" });
//		    context.registerShutdownHook();
//		    context.start();
			
			ApplicationContextUtil.getInstance();
			
		}catch(Exception e){
			logger.error("context", e);
		}
		logger.info(" timer 服务启动完毕---------------------------");
	}
	
	public static void main(String[] args) {
		startTimer(args);
	}

}
