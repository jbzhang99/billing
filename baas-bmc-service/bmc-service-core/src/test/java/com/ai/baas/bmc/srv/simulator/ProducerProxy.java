package com.ai.baas.bmc.srv.simulator;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerProxy {
	//private static String kafka_name = "ECBCA29571714183B23A630E2311DD66_MDS025_627475234";
	//private static String kafka_name = "baas_bmc_service_in_queue";
	//private static String kafka_name = "bmc_service_out_queue";
	private static String kafka_name = "topic20170411";
	//private static String kafka_name = "baas_bmc_service_out_queue";
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
		Properties props = new Properties();
		//props.put("metadata.broker.list", "10.1.234.162:9092");
		//props.put("metadata.broker.list", "10.1.241.36:39091,10.1.241.37:39091");
		//props.put("metadata.broker.list", "10.1.245.7:39091,10.1.245.7:49091,10.1.245.7:59091");
		//props.put("metadata.broker.list", "10.1.234.162:39091,10.1.234.163:39091,10.1.234.164:39091");
		props.put("metadata.broker.list", "10.1.234.160:38191");
		//props.put("metadata.broker.list", "10.248.4.4:2187,10.248.4.4:2188,10.248.4.4:2189");
        props.put("serializer.class", "kafka.serializer.StringEncoder"); 
        props.put("request.required.acks", "1");
        producer = new Producer<String, String>(new ProducerConfig(props));
	}
	
	public void sendMessage(String message){
		//KeyedMessage<String, String> data = new KeyedMessage<String, String>();
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(kafka_name, message);
		producer.send(data);
	}
	
	public void close(){
		if(producer != null){
			producer.close();
		}
	}
}
