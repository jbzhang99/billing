package com.ai.runner.center.ctp.rtm.core.cache;

import org.apache.commons.lang.StringUtils;


public class CacheProxy {

	private static ICacheClient cacheClient;
	private String strCache;
	private String deployEnv;
	public ICacheClient getCacheClient() {
		return cacheClient;
	}

	@SuppressWarnings("static-access")
	public void setCacheClient(ICacheClient cacheClient) {
		this.cacheClient = cacheClient;
	}


	
	public ICacheClient getCache(){
		if(strCache!=null||"Y".equals(StringUtils.upperCase(strCache))){
			if("standalone".equals(deployEnv)){
				
			}else if("cluster".equals(deployEnv)){
				cacheClient = new CacheClusterClient();
			}
		}
		return cacheClient;
	}

	public String getStrCache() {
		return strCache;
	}

	public void setStrCache(String strCache) {
		this.strCache = strCache;
	}

	public String getDeployEnv() {
		return deployEnv;
	}

	public void setDeployEnv(String deployEnv) {
		this.deployEnv = deployEnv;
	}
	
}
