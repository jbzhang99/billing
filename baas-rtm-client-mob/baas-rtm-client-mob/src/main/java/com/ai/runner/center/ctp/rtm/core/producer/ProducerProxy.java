package com.ai.runner.center.ctp.rtm.core.producer;

import org.apache.commons.lang.StringUtils;



public class ProducerProxy {

	@SuppressWarnings("unused")
	private static String strategy = "kafka";//默认策略为原生kafka消息
	private static IProducer producer = null;
	private static ProducerProxy instance = null;
	private static String strStrategy ;

	public IProducer getProducer() {
		return producer;
	}

	@SuppressWarnings("static-access")
	public void setProducer(IProducer producer) {
		this.producer = producer;
	}

	public  String getStrStrategy() {
		return strStrategy;
	}

	@SuppressWarnings("static-access")
	public  void setStrStrategy(String strStrategy) {
		this.strStrategy = strStrategy;
	}

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

		//strategy = StringUtils.lowerCase(strStrategy);

		//producer = ProducerFactory.createProduceerObj(strategy);
		producer.init();
	}

	public void sendMessage(String message){
		producer.sendMessage(message);
	}

	public void close(){
		producer.close();
	}
	
}
