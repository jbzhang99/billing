package com.ai.runner.center.ctp.rtm.core.producer;

import org.apache.commons.lang.StringUtils;

import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class ProducerProxy {

	private static String strategy = "kafka";//默认策略为原生kafka消息
	private static IProducer producer = null;
	private static ProducerProxy instance = null;
	//synchronized
	public static ProducerProxy getInstance(){
		if(instance == null){
			synchronized(ProducerProxy.class){
				if(instance == null){
					instance = new ProducerProxy();
					init();
				}
			}
		}
		return instance;
	}
	
	private static void init(){
		String strStrategy = (String)PropertiesUtil.getValue("ctp.rtm.producer.strategy");
		if(StringUtils.isNotBlank(strStrategy)){
			strategy = StringUtils.lowerCase(strStrategy);
		}
		producer = ProducerFactory.createProduceerObj(strategy);
		producer.init();
	}
	
	public  void sendMessage(String message){
	    System.err.println("当前对象："+producer);
		producer.sendMessage(message);
		
	}
	
	public void close(){
		producer.close();
	}

}
