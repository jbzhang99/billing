package com.ai.runner.center.bmc.resdeposit.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Date: 2016年5月20日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public class FailMsgLogHandleMain {
	
	public static void main(String []args){
	
		String[] configs = { "/context/core-context.xml" };
		
		@SuppressWarnings("resource")
		ApplicationContext cxt = new ClassPathXmlApplicationContext(configs);
		
		FailMsgLogHandle handle = cxt.getBean(FailMsgLogHandle.class);
		String systemId = args[0];
		String tenantId = args[1];
		String date = args[2];
		handle.doFailMsgLog(systemId,tenantId,date);
		
	}
	
}
