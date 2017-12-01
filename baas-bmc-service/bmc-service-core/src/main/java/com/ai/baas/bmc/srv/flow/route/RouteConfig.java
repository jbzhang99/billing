package com.ai.baas.bmc.srv.flow.route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.util.BaasConstants;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

@Component
public class RouteConfig {
	private static Logger logger = LoggerFactory.getLogger(RouteConfig.class);
	private static Map<String,String> routeMapping = Maps.newHashMap();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void loadConf(){
		jdbcTemplate.query(assembleQuerySQL(BaasConstants.BMC_ROUTE_PREFIX), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String routeKey = Joiner.on("").join(
						BaasConstants.BMC_ROUTE_PREFIX,
						BaasConstants.COMMON_SPLIT,
						StringUtils.defaultString(rs.getString("route_params")));
			    routeMapping.put(routeKey, StringUtils.defaultString(rs.getString("route_class")));
			}
		});
	}
	
	private String assembleQuerySQL(String route_type){
		StringBuilder strSql = new StringBuilder();
		strSql.append("select r.route_params,r.route_class ");
		strSql.append("from pub_service_route r ");
		strSql.append("where r.route_type = '");
		strSql.append(route_type).append("'");
		return strSql.toString();
	}
	
	public static String getRouteValue(String routeKey){
		return routeMapping.get(routeKey);
	}
	
}
