package com.ai.baas.bmc.topology.adapt.processor;

import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.bmc.topology.cache.BasicInfo;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.util.BaseConstants;
import com.esotericsoftware.kryo.util.IdentityMap.Entry;

public class VIVRuleProcessor implements IRuleProcessor {
	private BasicInfo basicInfo = new BasicInfo();
	
	
	@Override
	public void buildRuleAdapt(Map<String, String> data) throws Exception {
		System.out.println("VIVRuleProcessor-----------------");
		for(java.util.Map.Entry<String, String> entry:data.entrySet()){
			System.out.println("the key is "+entry.getKey()+"  the value is "+entry.getValue());
		}
		//开始进行开始时间的拼接
		String startDate=data.get(BmcConstants.START_DATE);
		String startTimeIn=data.get(BmcConstants.START_TIME_IN);
		StringBuilder startTime=new StringBuilder();
		startTime.append(startDate).append(startTimeIn);
		data.put(BaseConstants.START_TIME, startTime.toString());
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaseConstants.SERVICE_ID, data.get(BaseConstants.SERVICE_ID));
		System.out.println("the service_id is "+data.get(BaseConstants.SERVICE_ID));
	    params.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
		basicInfo.setUserData(data, params);
		basicInfo.setProductData(data); 
		basicInfo.setApnMatch(data);
		
		
//		Map<String,String> params = new TreeMap<String,String>();
//	    params.put("price_code", "999"); 
//	    params.put("tenant_id", "VIV-BYD");
//	    List<Map<String, String>> results = CacheClient.getInstance().doQuery("cp_price_info", params);
//	    for (Map<String, String> map : results){
//	        for(Entry<String, String> result:map.entrySet()){
//	           System.out.println("the key is "+result.getKey()+"="+result.getValue());
//	        }
//	      }
	}

}
