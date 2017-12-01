package com.ai.runner.center.ctp.rtm.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.runner.center.ctp.rtm.flow.RtmFlow;


public class RtmShell {

	private static Logger logger = LoggerFactory.getLogger(RtmShell.class);
	
	
	public static int runTool(String[] args){
		int exitCode = 0;
		
		try{
			
			ApplicationContext context = new ClassPathXmlApplicationContext("app-properties.xml");
			RtmFlow rtmFlow = (RtmFlow) context.getBean("rtmFlow");
			logger.debug("[读取配置文件成功!]");
			rtmFlow.startRun();
			//System.out.println(222);
			logger.debug("[程序加载成功]");

		}catch(Exception e){
			logger.error("context", e);
			exitCode = 1;
		}
		return exitCode;
	}
	
	
	public static void main(String[] args) {
		System.out.println(111);
		int code = runTool(args);
		if(code == 0){
			logger.debug("[后台扫描程序启动成功!]");
		}else{
			logger.debug("{后台扫描程序启动失败!}");
		}
	}

}
