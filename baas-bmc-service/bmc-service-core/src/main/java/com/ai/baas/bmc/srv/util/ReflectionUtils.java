package com.ai.baas.bmc.srv.util;

import com.ai.baas.bmc.srv.flow.adapt.IRuleProcessor;


public class ReflectionUtils {

	@SuppressWarnings("unchecked")
	public static IRuleProcessor getRuleProcessorObj(String strClazz) throws Exception{
		Class<IRuleProcessor> clazz = (Class<IRuleProcessor>)Class.forName(strClazz);
		return clazz.newInstance();
	}
	
}
