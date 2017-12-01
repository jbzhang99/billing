package com.ai.baas.bmc.srv.flow.adapt;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.cache.BasicInfo;
import com.ai.baas.bmc.srv.util.BaasConstants;

public class EciticRuleProcessor implements IRuleProcessor {
	private static Logger logger = LoggerFactory.getLogger(EciticRuleProcessor.class);
	private BasicInfo basicInfo = new BasicInfo();
	
	@Override
	public void buildRuleAdapt(Map<String, String> data) throws BusinessException {
		//logger.debug("EciticRuleProcessor-----------------");
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.TENANT_ID, data.get(BaasConstants.TENANT_ID));
		params.put(BaasConstants.SERVICE_ID, data.get(BaasConstants.SERVICE_ID));
		basicInfo.setUserData(data, params);
		basicInfo.setProductData(data);
	}

}
