package com.ai.baas.rtm.initload;

import java.util.HashMap;
import java.util.Map;



import com.ai.baas.rtm.constants.RtmCacheMapper;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class McsClient {
	public static ICacheClient client;
	public static void getClient() {
		 client=MCSClientFactory.getCacheClient(
				 RtmCacheMapper.RTM_CACHE_PARAM);
	}
	public static void main(String[] args){
		String a="MSG";
		String b="source_id";
		String c="1:1";
		getClient();
		Map<String,String> test=new HashMap<String,String>();
		McsClient.client.set(a,b);
		System.out.println("the number is "+McsClient.client.get(a));
//		test=McsClient.client.hgetAll(a);
//		for(Entry<String, String> result:test.entrySet()){
//			System.out.println("the key is "+result.getKey());
//			System.out.println("the value is "+result.getValue());
//		}
		//McsClient.client.hset(a,b,c);
	}

}
