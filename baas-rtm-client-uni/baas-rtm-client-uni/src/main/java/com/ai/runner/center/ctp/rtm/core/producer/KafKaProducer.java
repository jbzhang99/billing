package com.ai.runner.center.ctp.rtm.core.producer;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafKaProducer implements IProducer {
	
	private String kafka_name = "";
	private Producer<String, String> producer = null;
	
	@Override
	public void init() {
		kafka_name = (String)PropertiesUtil.getValue("ctp.rtm.message.topic");
		Properties props = new Properties();
		props.put("metadata.broker.list", StringUtils.trim(PropertiesUtil.getValue("metadata.broker.list")));
        props.put("serializer.class", StringUtils.trim(PropertiesUtil.getValue("serializer.class"))); 
        props.put("request.required.acks", StringUtils.trim(PropertiesUtil.getValue("request.required.acks")));
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

}
