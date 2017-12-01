package com.ai.runner.center.bmc.deduct.consumer;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.Module.SetupContext;

import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.paas.ipaas.mds.MsgConsumerFactory;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.runner.center.bmc.deduct.consumer.AuthConfig;
/**
 * 测试类
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 */
public class AuthProduct {
	public static Random random = new Random();
	
	static String readTxt(String filePath){
       File root = new File(filePath);
       String s = null;
       try{
           BufferedReader br = new BufferedReader(new FileReader(root));//读取文件
           s = br.readLine();
           if(s==null){System.out.println("字符串不能为空");}
           br.close();  
       }catch(Exception e){
           e.printStackTrace();
       }
       return s;
    }
	 
	public static void main(String[] args){
		Properties prop=AuthConfig.getAuthConfig();
		String srvId=prop.getProperty("srvId");
		String authAddr=prop.getProperty("authAddr");
		String authPid=prop.getProperty("authPid");
		String servicePasswd=prop.getProperty("servicePasswd");
		String msgTopic=prop.getProperty("msgTopic");
		System.out.println("the length of the topic is "+msgTopic.length());
		System.out.println("the srvId is:"+srvId+",  the authAddr is:"+authAddr+",  the authPid is:"+authPid+", the servicePasswd is:"+servicePasswd);
		try{
			AuthDescriptor ad=new AuthDescriptor(authAddr, authPid, servicePasswd, srvId);
			IMessageSender msgSender = MsgSenderFactory.getClient(ad, msgTopic);
			
			//String filePath ="./msg.txt"; //"E://msg.txt" ; 文件路径
			//String msg = null;
			//msg = readTxt(filePath);
			//msgSender.send(msg , random.nextInt(2)%2);        
			msgSender.send("{\"amount\":\"1024\",\"amount_type\":\"DATA\",\"subs_id\":\"1\",\"business_id\":\"RUNNER-VIV\",\"acct_id\":\"60000172359\",\"tenant_id\":\"VIV-BYD\",\"event_id\":\"1064851400609CMIOT20160414140538\"}", random.nextInt(2)%2);		
			//System.out.println("the msg is"+msg);
			System.out.println("finish");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args){
//		AuthConsumer auth=new AuthConsumer();
//		auth.setUp(AuthConfig.getAuthConfig());
//		msgSender.
//		while (true) {
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}

