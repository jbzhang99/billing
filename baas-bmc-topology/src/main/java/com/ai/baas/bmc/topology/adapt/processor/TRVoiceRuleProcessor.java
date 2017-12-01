package com.ai.baas.bmc.topology.adapt.processor;

import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.bmc.topology.cache.BasicInfo;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.util.BaseConstants;

public class TRVoiceRuleProcessor implements IRuleProcessor {
	private BasicInfo basicInfo = new BasicInfo();
	
	
	@Override
	public void buildRuleAdapt(Map<String, String> data) throws BusinessException {
		System.out.println("TRVoiceRuleProcessor-----------------");
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
		//params.put("service_id", "17090010015");
		//params.put("service_id", "17012345678");
		params.put(BaseConstants.SERVICE_ID, data.get(BaseConstants.SERVICE_ID));
		basicInfo.setUserData(data, params);
		//data.put("service_num", data.get("service_num"));
		//data.put("service_num", "17012345678");
		basicInfo.setProductData(data);

	}
	
	

}
