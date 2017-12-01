package com.ai.baas.bmc.topology.adapt;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.adapt.processor.IRuleProcessor;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.bmc.topology.util.ReflectionUtils;
import com.ai.baas.storm.util.BaseConstants;

/**
 * 规则适配路由映射
 * @author majun
 * @since 2016.03.29
 *
 */
public class RouteMapping {
	private static Logger logger = LoggerFactory.getLogger(RouteMapping.class);
	private RouteConfig routeConfig = new RouteConfig();
	
	public RouteMapping(){
		routeConfig.loadConf();
	}
	
	public IRuleProcessor getProcessorByRoute(Map<String, String> data) throws Exception{
		String key = assembleKey(data);
		String clazz = routeConfig.getRouteValue(key);
		if(StringUtils.isBlank(clazz)){
			throw new Exception(assembleErrorMsg(key));
		}
		return ReflectionUtils.getRuleProcessorObj(clazz);
	}
	
	
	private String assembleKey(Map<String, String> data){
		StringBuilder key = new StringBuilder();
		key.append(BmcConstants.BMC_ROUTE_PREFIX).append(BaseConstants.COMMON_SPLIT);
		key.append(data.get(BaseConstants.TENANT_ID)).append(BaseConstants.COMMON_SPLIT);
		key.append(data.get(BaseConstants.SERVICE_TYPE)).append(BaseConstants.COMMON_SPLIT);
		key.append(data.get(BaseConstants.SOURCE));
		return key.toString();
	}
	
	private String assembleErrorMsg(String key){
		StringBuilder error = new StringBuilder();
		error.append("[key=").append(key).append("]");
		error.append("没有配置路由class参数");
		return error.toString();
	}
	
}
