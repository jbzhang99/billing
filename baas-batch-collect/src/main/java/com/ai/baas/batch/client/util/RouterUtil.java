package com.ai.baas.batch.client.util;

import com.ai.baas.batch.client.service.ICalProcessor;

public class RouterUtil {

	@SuppressWarnings("unchecked")
	public static ICalProcessor getProcessorByRoute(String strClazz)throws Exception{
		Class<ICalProcessor> clazz = (Class<ICalProcessor>)Class.forName(strClazz);
		return clazz.newInstance();
	}

}
