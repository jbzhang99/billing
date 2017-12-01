package com.ai.baas.bmc.service.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.baas.bmc.util.DshmUtil;

public class SplitRowkey {
    private static final Logger LOGGER = LogManager.getLogger(SplitRowkey.class);
	public List<Map<String,String>> getSubsInfo(String tenantId,String serviceId){
		Map<String,String> params = new TreeMap<String,String>();
		params.put("service_id", serviceId);
		params.put("tenant_id", tenantId);
		List<Map<String, String>> results=DshmUtil.getClient().list("bl_userinfo")
				.where(params) 
				.executeQuery(DshmUtil.getCacheClient());
//		List<Map<String, String>> results=new ArrayList<Map<String,String>>();
//		Map<String, String> result=new HashMap<String, String>();
//		result.put("subs_id", "s2681");
//		result.put("cust_id", "2681");
//		result.put("acct_id", "2681");
//		result.put("service_id", "iccid2681");
//		results.add(result);
		return results;
	}
	
	public List<Map<String, String>> getKeyData(String rowKey,List<Map<String, String>> results ){
		String[] keyDatas=StringUtils.splitPreserveAllTokens(rowKey, ":");
		List<Map<String, String>> rowKeyData=new ArrayList<Map<String,String>>();
		for(Map<String,String> result:results){
			for(String keyData:keyDatas){
				Map<String, String> row=new HashMap<String,String>();
				String key;
				key=result.get(keyData);
				LOGGER.info("the param name is "+keyData+"  the value is "+key);
				row.put(keyData, key);
				rowKeyData.add(row);
			}
		}
//		for(java.util.Map.Entry<String, String> entry:rowKeyData.entrySet()){
//			System.out.println("the map test name is "+entry.getKey()+"  the value is "+entry.getValue());
//		}
		return rowKeyData;
	}
	

}
