package com.ai.baas.batch.client.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class UserIdUtil {
	private static ICacheClient cacheClient;
	private static IDshmClient client=new DshmClient();
	
	public static List<Map<String, String>> getUserId(String instanceId) {
		//for(Map<String, String> data:dataArray){
			//String instanceId=data.get("instance_id");
		    cacheClient=DshmUtil.getCacheClient();
			Map<String,String> params = new TreeMap<String,String>();
			params.put("instance_id", instanceId);
			List<Map<String, String>> results=client.list("bl_userinfo_zx")
					.where(params)
					.executeQuery(cacheClient);
			System.out.println("the size of the result is "+results.size());
			if(results.size()!=0)
						return results;
		return null;
	}
}
