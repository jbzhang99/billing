package com.ai.runner.center.ctp.rtm.core.cache;

import org.apache.commons.lang.StringUtils;

import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class CacheProxy {

	private static ICacheClient cacheClient;
	
	static{
		String strCache = (String)PropertiesUtil.getValue("ctp.rtm.packet.psn.accumulate.cache");
		if(StringUtils.isNotBlank(strCache)||"Y".equals(StringUtils.upperCase(strCache))){
			String deployEnv = (String)PropertiesUtil.getValue("ctp.rtm.cache.deploy.env");
			if("standalone".equals(deployEnv)){
				cacheClient = new CacheClient();
			}else if("cluster".equals(deployEnv)){
				cacheClient = new CacheClusterClient();
			}
		}
	}
	
	public static ICacheClient getCache(){
		return cacheClient;
	}
	
}
