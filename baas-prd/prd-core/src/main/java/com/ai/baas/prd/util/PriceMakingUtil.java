package com.ai.baas.prd.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMapping;
import com.ai.baas.prd.service.atom.interfaces.ICpPricemakingMappingAtomSV;
import com.google.common.base.Joiner;

@Component
public class PriceMakingUtil {
	private static Logger logger = LoggerFactory.getLogger(PriceMakingUtil.class);
	public static final String FIELD_SPLIT = new String(new char[] { (char) 1 });
	private static Map<String,String> mappingRule = new HashMap<String,String>();
	
	@Autowired
	ICpPricemakingMappingAtomSV cpPricemakingMappingAtomSV;
	
	@PostConstruct
	public void initMappingRule() {
		String mappingKey = "";
		for(CpPricemakingMapping cpPricemakingMapping:cpPricemakingMappingAtomSV.queryAllPricemakingMappingRule()){
			mappingKey = Joiner.on(FIELD_SPLIT).join(
					StringUtils.upperCase(cpPricemakingMapping.getTenantId()),
					StringUtils.upperCase(cpPricemakingMapping.getMappingBefore()));
			if(!mappingRule.containsKey(mappingKey)){
				mappingRule.put(mappingKey, cpPricemakingMapping.getMappingAfter());
			}
		}
		logger.info("加载cp_pricemaking_mapping配置成功!");
	}
	
	
	public static String ruleMapping(String tenant_id, String before) {
		String mappingKey = Joiner.on(FIELD_SPLIT).join(StringUtils.upperCase(tenant_id),StringUtils.upperCase(before));
		String rtnValue = mappingRule.get(mappingKey);
		return StringUtils.isBlank(rtnValue) ? before : rtnValue;
	}
	
}
