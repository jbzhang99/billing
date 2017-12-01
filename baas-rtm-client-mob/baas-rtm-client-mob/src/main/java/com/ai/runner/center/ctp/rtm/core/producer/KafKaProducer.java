package com.ai.runner.center.ctp.rtm.core.producer;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafKaProducer implements IProducer {
	
	private String kafka_name = "";
	private String metadatas;
	private String serializerClass;
	private String reqAcks;
	private Producer<String, String> producer = null;
	
	@Override
	public void init() {
		
		Properties props = new Properties();
		props.put("metadata.broker.list", StringUtils.trim(metadatas));
        props.put("serializer.class", StringUtils.trim(serializerClass)); 
        props.put("request.required.acks", StringUtils.trim(reqAcks));
        producer = new Producer<String, String>(new ProducerConfig(props));
	}

	@Override
	public void sendMessage(String message) {
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(kafka_name, message);
		producer.send(data);
	}
	
	@Override
	public void close(){
		if(producer != null){
			producer.close();
		}
	}

	public String getKafka_name() {
		return kafka_name;
	}

	public void setKafka_name(String kafka_name) {
		this.kafka_name = kafka_name;
	}

	public String getMetadatas() {
		return metadatas;
	}

	public void setMetadatas(String metadatas) {
		this.metadatas = metadatas;
	}

	public String getSerializerClass() {
		return serializerClass;
	}

	public void setSerializerClass(String serializerClass) {
		this.serializerClass = serializerClass;
	}

	public String getReqAcks() {
		return reqAcks;
	}

	public void setReqAcks(String reqAcks) {
		this.reqAcks = reqAcks;
	}

	
	
}
