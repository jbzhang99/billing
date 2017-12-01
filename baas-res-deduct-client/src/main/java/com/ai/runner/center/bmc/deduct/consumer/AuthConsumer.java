package com.ai.runner.center.bmc.deduct.consumer;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.paas.ipaas.mds.MsgConsumerFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.runner.center.bmc.deduct.consumer.AuthConfig;

public class AuthConsumer {

	private static IMessageConsumer msgConsumer = null;
	public static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:context/core-context.xml");
	private static final Logger LOG = LoggerFactory.getLogger(AuthConsumer.class);
	
	void setUp(Properties prop) {
		
		String srvId = prop.getProperty("srvId");
		String authAddr = prop.getProperty("authAddr");
		String authPid = prop.getProperty("authPid");
		String servicePasswd = prop.getProperty("servicePasswd");
		String msgTopic = prop.getProperty("msgTopic");
		
		try {
			AuthDescriptor ad = new AuthDescriptor(authAddr, authPid, servicePasswd, srvId);
			IMsgProcessorHandler msgProcessorHandler = new MsgProcessorHandlerImpl(AuthConsumer.context);
			
			msgConsumer = MsgConsumerFactory.getClient(ad, msgTopic, msgProcessorHandler);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("setUp error ",e);
		}
	}

	public static void main(String[] args) {
		LOG.info("main thread start authConsumer...");
		System.out.println("main thread start authConsumer...");
		AuthConsumer auth = new AuthConsumer();
		auth.setUp(AuthConfig.getAuthConfig());	
		msgConsumer.start();
		LOG.info("start authConsumer complete!");
		System.out.println("start authConsumer complete!");
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				LOG.error("authConsumer : ",e);
			}
		}
	}

}
