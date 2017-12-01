package com.ai.baas.bmc.topology.adapt.processor;

import java.util.Map;

public interface IRuleProcessor {

	void buildRuleAdapt(Map<String, String> data) throws Exception;
	
}
