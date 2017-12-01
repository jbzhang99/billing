package com.ai.runner.center.ctp.rtm.core.producer;


public class ProducerFactory {

	
	public static IProducer createProduceerObj(String strategy){
		IProducer producer = null;
		if("kafka".equals(strategy)){
			producer = new KafKaProducer();
		}else if("mds".equals(strategy)){
			producer = new MDSProducer();
		}
		return producer;
	}
	
	
}
