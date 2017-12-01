package com.ai.baas.bmc.topology.util;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.topology.adapt.processor.IRuleProcessor;

public class ReflectionUtils {

	@SuppressWarnings("unchecked")
	public static IRuleProcessor getRuleProcessorObj(String strClazz) throws Exception{
		Class<IRuleProcessor> clazz = (Class<IRuleProcessor>)Class.forName(strClazz);
		return clazz.newInstance();
	}
	
	
}
