package com.ai.baas.bmc.topology.adapt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.jdbc.JdbcTemplate;
import com.ai.baas.storm.util.BaseConstants;

public class RouteConfig {
	private static Logger logger = LoggerFactory.getLogger(RouteConfig.class);
	private Map<String,String> routeMapping = new HashMap<String,String>();
	
	public void loadConf(){
		List<Map<String,Object>> routes = JdbcTemplate.defaultQuery(assembleQuerySQL(BmcConstants.BMC_ROUTE_PREFIX), new MapListHandler());
		for(Map<String,Object> route:routes){
			StringBuilder key = new StringBuilder();
			key.append(BmcConstants.BMC_ROUTE_PREFIX).append(BaseConstants.COMMON_SPLIT);
			key.append(ObjectUtils.toString(route.get("route_params")));
			
			routeMapping.put(key.toString(), ObjectUtils.toString(route.get("route_class")));
		}
	}
	
	private String assembleQuerySQL(String route_type){
		StringBuilder strSql = new StringBuilder();
		strSql.append("select r.route_params,r.route_class ");
		strSql.append("from pub_service_route r ");
		strSql.append("where r.route_type = '");
		strSql.append(route_type).append("'");
		return strSql.toString();
	}
	
	
	public String getRouteValue(String routeKey){
		return routeMapping.get(routeKey);
	}
	
	
	
	
}
