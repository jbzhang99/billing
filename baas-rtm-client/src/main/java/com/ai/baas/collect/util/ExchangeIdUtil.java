package com.ai.baas.collect.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class ExchangeIdUtil {
	private static ICacheClient cacheClient;
	private static IDshmClient client = new DshmClient();

	public static List<Map<String, String>> getServiceId(String tenantId, String instanceId) {
		// for(Map<String, String> data:dataArray){
		// String instanceId=data.get("instance_id");
		cacheClient = DshmUtil.getCacheClient();
		Map<String, String> params = new TreeMap<String, String>();
		params.put("tenant_id", tenantId);
		params.put("out_instance_res_id", instanceId);
		List<Map<String, String>> results = client.list("bl_exchange_info")
				.where(params).executeQuery(cacheClient);
		System.out.println("the size of the result is " + results.size());
		if (results.size() != 0)
			return results;
		return null;
	}

}
