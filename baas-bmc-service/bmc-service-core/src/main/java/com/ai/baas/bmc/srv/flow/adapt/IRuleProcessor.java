package com.ai.baas.bmc.srv.flow.adapt;

import java.util.Map;

import com.ai.baas.bmc.srv.failbill.BusinessException;

public interface IRuleProcessor {
	
	void buildRuleAdapt(Map<String, String> data) throws BusinessException;
}
