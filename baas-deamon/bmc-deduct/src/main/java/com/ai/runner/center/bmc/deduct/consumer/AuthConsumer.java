package com.ai.runner.center.bmc.deduct.consumer;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.Module.SetupContext;
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

	void setUp(Properties prop) {
		String srvId = prop.getProperty("srvId");
		String authAddr = prop.getProperty("authAddr");
		String authPid = prop.getProperty("authPid");
		String servicePasswd = prop.getProperty("servicePasswd");
		String msgTopic = prop.getProperty("msgTopic");
		System.out.println("the length of the topic is " + msgTopic.length());
		System.out.println("the srvId is:" + srvId + ",  the authAddr is:" + authAddr + ",  the authPid is:" + authPid + ", the servicePasswd is:" + servicePasswd);
		try {
			AuthDescriptor ad = new AuthDescriptor(authAddr, authPid, servicePasswd, srvId);
			IMsgProcessorHandler msgProcessorHandler = new MsgProcessorHandlerImpl(AuthConsumer.context);
			System.out.println("hahahahahha");
			msgConsumer = MsgConsumerFactory.getClient(ad, msgTopic, msgProcessorHandler);
			System.out.println("hahahahahha2222222");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AuthConsumer auth = new AuthConsumer();
		auth.setUp(AuthConfig.getAuthConfig());	
		msgConsumer.start();
		
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
