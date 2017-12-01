package com.ai.baas.amc.test.mds;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MDSProducer {
	//private static String kafka_name = "ECBCA29571714183B23A630E2311DD66_MDS024_120558868";
	private static String kafka_name = "ECBCA29571714183B23A630E2311DD66_MDS023_1528582415";
	
	@Test
	public void testAPMds(){
		MDSProducer producer = new MDSProducer();
		String msg = producer.assembleData();
		System.out.println("计费输出消息:"+msg);
		producer.sendMessage(msg);
	}
	
	private void sendMessage(String message){
		Properties props = new Properties();
		props.put("metadata.broker.list", "10.1.245.7:39091,10.1.245.7:49091,10.1.245.7:59091");
        props.put("serializer.class", "kafka.serializer.StringEncoder"); 
        props.put("request.required.acks", "1");
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(kafka_name, message);
		producer.send(data);
		producer.close();
	}
	
//	private String assembleData(){
//		Map<String,Object> msgData = Maps.newHashMap();
//		msgData.put("tenant_id", "ECITIC");
//		msgData.put("service_type", "ECS");
//		msgData.put("source", "ALIECS");
//		msgData.put("bsn", "1473236583428");
//		msgData.put("sn", "bf0b67db-c34d-465a-bf38-22c79f7d8d3020160906000000TIME116");
//		msgData.put("arrival_time", "20160907162304");
//		msgData.put("account_period", "201611");
//		msgData.put("service_id", "be1f8e7a-d988-45dc-8f3a-c2a68cf1f761");
//		msgData.put("instance_id", "bf0b67db-c34d-465a-bf38-22c79f7d8d30");
//		msgData.put("usage_amount", "3080");
//		msgData.put("start_time", "20160906000000");
//		msgData.put("region_id", "cn-beijing");
//		msgData.put("record_type", "TIME");
//		msgData.put("subs_id", "542");
//		msgData.put("cust_id", "542");
//		msgData.put("acct_id", "482");
//		msgData.put("product_id", "9001,9002,4310");
//		msgData.put("priceCode", "4310");
//		
//		List<Map<String,String>> msgFeeDatas = Lists.newArrayList();
//		msgData.put("fee_info", msgFeeDatas);
//		
//		Map<String,String> feeMap = Maps.newHashMap();
//		feeMap.put("fee", "0");
//		feeMap.put("subject", "");
//		msgFeeDatas.add(feeMap);
//		
//		feeMap = Maps.newHashMap();
//		feeMap.put("fee", "0");
//		feeMap.put("subject", "");
//		msgFeeDatas.add(feeMap);
//		
//		feeMap = Maps.newHashMap();
//		feeMap.put("fee", "10880.000");
//		feeMap.put("subject", "ECS01");
//		feeMap.put("apportion_list", "[{\"cost_center_id\":\"3b9c2177-968f-42fb-accd-fcd850f9d4b5\",\"value\":\"1.00\",\"method\":\"ratio\",\"acct_id\":\"482\"}]");
//		msgFeeDatas.add(feeMap);
//		
////		feeMap = Maps.newHashMap();
////		feeMap.put("fee", "1.000");
////		feeMap.put("subject", "ECS02");
////		feeMap.put("apportion_list", "[{\"cost_center_id\":\"3b9c2177-968f-42fb-accd-fcd850f9d4b5\",\"value\":\"1.00\",\"method\":\"ratio\",\"acct_id\":\"666\"}]");
////		msgFeeDatas.add(feeMap);
//
//		return JSON.toJSONString(msgData);
//	}
	
	
//	private String assembleData(){
//		Map<String,Object> msgData = Maps.newHashMap();
//		msgData.put("tenant_id", "ECITIC");
//		msgData.put("service_type", "KVS");
//		msgData.put("source", "ALIKVS");
//		msgData.put("bsn", "1473236583428");
//		msgData.put("sn", "7bd62a4c-3445-439f-8b4d-185603048c2920160906230000");
//		msgData.put("arrival_time", "20160907162314");
//		msgData.put("account_period", "201611");
//		msgData.put("service_id", "d7b2ff8b-48ec-4ea6-b81e-d5506cb56a48");
//		msgData.put("instance_id", "7bd62a4c-3445-439f-8b4d-185603048c29");
//		msgData.put("usage_amount", "1");
//		msgData.put("start_time", "20160906230000");
//		msgData.put("region_id", "cn-qingdao");
//		msgData.put("record_type", "");
//		msgData.put("subs_id", "546");
//		msgData.put("cust_id", "543");
//		msgData.put("acct_id", "483");
//		msgData.put("product_id", "4393");
//		msgData.put("priceCode", "3911");
//		
//		List<Map<String,String>> msgFeeDatas = Lists.newArrayList();
//		msgData.put("fee_info", msgFeeDatas);
//		
//		Map<String,String> feeMap = Maps.newHashMap();
//		feeMap.put("fee", "9000.000");
//		feeMap.put("subject", "KVS01");
//		feeMap.put("apportion_list", "[{\"cost_center_id\":\"2de16a5f-34e9-41a2-b1d8-e057622564e7\",\"value\":\"1.00\",\"method\":\"ratio\",\"acct_id\":\"483\"}]");
//		//feeMap.put("apportion_list", "[{\"cost_center_id\":\"2de16a5f-34e9-41a2-b1d8-e057622564e7\",\"value\":\"0.5\",\"method\":\"ratio\",\"acct_id\":\"483\"},{\"cost_center_id\":\"2de16a5f-34e9-41a2-b1d8-e057622564e7\",\"value\":\"0.5\",\"method\":\"ratio\",\"acct_id\":\"483\"}]");
//		msgFeeDatas.add(feeMap);
//
//		return JSON.toJSONString(msgData);
//	}
	
	private String assembleData(){
		Map<String,Object> msgData = Maps.newHashMap();
		msgData.put("tenant_id", "TR");
		msgData.put("acct_id", "1234");
		return JSON.toJSONString(msgData);
	}
	
}
