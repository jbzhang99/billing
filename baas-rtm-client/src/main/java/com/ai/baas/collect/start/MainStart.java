package com.ai.baas.collect.start;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.baas.collect.getfile.GetFiles;
import com.ai.baas.collect.service.ServiceStart;
import com.ai.baas.collect.util.PropertiesUtil;
import com.ai.baas.collect.vo.ServiceParam;
import sun.misc.Signal;  
import sun.misc.SignalHandler; 

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: MainStart.java
 * @Description: 进程启动的主程序
 *
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月14日 下午4:15:09
 *
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2017年3月14日     hanzf           v1.0.0               创建
 */
public class MainStart {
	private static final String[] PATH = { "classpath:context/core-context.xml" };
    private static ClassPathXmlApplicationContext context;
    private static final Log logger = LogFactory.getLog(MainStart.class);
    
    static ServiceStart serviceStart;

	@SuppressWarnings({ "deprecation", "restriction" })
	public static void main(String[] args) {
		
		SignalDeal  getSignal = new SignalDeal();
		
		Signal.handle(new Signal("TERM"), getSignal);  
		
		// TODO Auto-generated method stub
		 try {
	            context = new ClassPathXmlApplicationContext(PATH);
	            context.registerShutdownHook();
	            context.start();
	            //开始加载配置
	            logger.error("开始加载batch.properties");
	            PropertiesUtil.load("batch.properties");
	            logger.error("加载batch.properties完成！");
	            
	            //启动相关线程
	            serviceStart=context.getBean(ServiceStart.class);
	            serviceStart.startService();	            	            

	        }catch(Exception e) {
	        	e.printStackTrace();
	        	logger.error("【采集程序启动错误：】" + e.getMessage(),e);
	        }finally{
	            //context.close();
	        }
		 
		 

	}
	
	public static void stopService() {
		// TODO Auto-generated method stub
		serviceStart.stopService();
		logger.error("所有线程退出完成，关闭context！");
		context.close();
		System.exit(0);
	}

}
