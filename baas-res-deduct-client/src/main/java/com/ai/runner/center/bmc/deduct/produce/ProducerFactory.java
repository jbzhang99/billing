package com.ai.runner.center.bmc.deduct.produce;

import com.ai.runner.center.bmc.deduct.constants.ProducerConst;


public class ProducerFactory {
	
	
	public static IProducer createProduceerObj(String strategy){
		IProducer producer = null;
		if(ProducerConst.MDS_PRODUCER.equals(strategy)){
			producer = new MDSProducer();
		}
		return producer;
	}
	
	
}
