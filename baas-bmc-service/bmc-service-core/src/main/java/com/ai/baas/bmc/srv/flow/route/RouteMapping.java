package com.ai.baas.bmc.srv.flow.route;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.adapt.IRuleProcessor;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.bmc.srv.util.ReflectionUtils;
import com.google.common.base.Joiner;


public class RouteMapping {

	public static IRuleProcessor getProcessorByRoute(Map<String, String> data) throws BusinessException,Exception{
		String key = assembleKey(data);
		String clazz = RouteConfig.getRouteValue(key);
		if(StringUtils.isBlank(clazz)){
			throw new BusinessException("BMC-B0007",assembleErrorMsg(key));
		}
		return ReflectionUtils.getRuleProcessorObj(clazz);
	}
	
	private static String assembleKey(Map<String, String> data){
		StringBuilder key = new StringBuilder();
		key.append(BaasConstants.BMC_ROUTE_PREFIX).append(BaasConstants.COMMON_SPLIT);
		key.append(data.get(BaasConstants.TENANT_ID)).append(BaasConstants.COMMON_SPLIT);
		key.append(data.get(BaasConstants.SERVICE_TYPE)).append(BaasConstants.COMMON_SPLIT);
		key.append(data.get(BaasConstants.SOURCE));
		return key.toString();
	}
	
	private static String assembleErrorMsg(String key){
		return Joiner.on("").join("[key=",key,"]没有配置路由class参数");
	}
	
	
}
