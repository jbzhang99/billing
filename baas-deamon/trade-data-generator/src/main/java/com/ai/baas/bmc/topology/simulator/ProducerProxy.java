package com.ai.baas.bmc.topology.simulator;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerProxy {
	//private static String kafka_name = "testkafka66";
	private static String topic = LoadProps.getKAFKA_TOPIC();
	private static Producer<String, String> producer = null;
	private static ProducerProxy instance = null;
	
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
//		Properties props = new Properties();
	    
		Properties props=LoadProps.getConfig();
        producer = new Producer<String, String>(new ProducerConfig(props));
	}
	
	public void sendMessage(String message){
		//KeyedMessage<String, String> data = new KeyedMessage<String, String>(kafka_name, message);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, message);
		producer.send(data);
	}
	
	public void close(){
		if(producer != null){
			producer.close();
		}
	}
}
