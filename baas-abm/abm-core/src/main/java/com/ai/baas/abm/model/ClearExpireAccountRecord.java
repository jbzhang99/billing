package com.ai.baas.abm.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.baas.abm.api.account.impl.AccountRecordSVImpl;

public class ClearExpireAccountRecord {

	public static final Logger LOGGER = LoggerFactory.getLogger(ClearExpireAccountRecord.class);
    public static final String[] PATH = { "classpath:context/core-context.xml" };
    public static ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) {
		
	   // 启动spring容器
	   LOGGER.info("启动spring容器,配置文件路径为"+PATH[0]);
       System.out.println("启动spring容器,配置文件路径为"+PATH[0]);
       context = new ClassPathXmlApplicationContext(PATH);
       context.registerShutdownHook();
       context.start();
       AccountRecordSVImpl recordSVImpl = context.getBean(AccountRecordSVImpl.class);
       recordSVImpl.clearExpireAccountRecord();
       System.out.println("finish");
	}
}
