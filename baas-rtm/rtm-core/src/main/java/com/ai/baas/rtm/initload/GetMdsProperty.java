package com.ai.baas.rtm.initload;

import java.util.Properties;

import com.ai.baas.rtm.utils.PropertiesUtil;

public class GetMdsProperty {
	public static  Properties properties =new Properties();
	
	public void loadProperty(){
		properties.setProperty("metadata.broker.list",PropertiesUtil.getValue("metadata.broker.list")); 
        properties.setProperty("serializer.class",PropertiesUtil.getValue("serializer.class")); 
        properties.setProperty("key.serializer.class",PropertiesUtil.getValue("key.serializer.class")); 
        properties.setProperty("partitioner.class",PropertiesUtil.getValue("partitioner.class")); 
        properties.setProperty("request.required.acks",PropertiesUtil.getValue("request.required.acks")); 
        properties.setProperty("queue.buffering.max.messages",PropertiesUtil.getValue("queue.buffering.max.messages")); 
        properties.setProperty("producer.type",PropertiesUtil.getValue("producer.type")); 
        properties.setProperty("message.send.max.retries",PropertiesUtil.getValue("message.send.max.retries")); 
        properties.setProperty("compression.codec",PropertiesUtil.getValue("compression.codec")); 
        properties.setProperty("request.timeout.ms",PropertiesUtil.getValue("request.timeout.ms")); 
        properties.setProperty("batch.num.messages",PropertiesUtil.getValue("batch.num.messages")); 
        properties.setProperty("send.buffer.bytes",PropertiesUtil.getValue("send.buffer.bytes")); 
        properties.setProperty("maxProducer",PropertiesUtil.getValue("maxProducer"));
	}
	
}
