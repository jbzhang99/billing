package com.ai.runner.center.bmc.deduct.produce;

import org.apache.commons.lang.StringUtils;

import com.ai.runner.center.bmc.deduct.constants.ProducerConst;
import com.ai.runner.center.bmc.deduct.util.PropertiesUtil;

public class ProducerProxy {

	private static String strategy = null;
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
		String strStrategy = PropertiesUtil.getProValue(ProducerConst.STRATEGY);
		if(StringUtils.isNotBlank(strStrategy)){
			strategy = StringUtils.lowerCase(strStrategy);
		}
		else {
			strategy = ProducerConst.MDS_STRATEGY;
		}
		producer = ProducerFactory.createProduceerObj(strategy);
		producer.init();
		
	}
	
	public  void sendMessage(String message){
	    
		producer.sendMessage(message);
		
	}
	
	public void close(){
		
		producer.close();
	}

}
