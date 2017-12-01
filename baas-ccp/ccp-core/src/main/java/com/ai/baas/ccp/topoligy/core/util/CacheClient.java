package com.ai.baas.ccp.topoligy.core.util;

import java.util.List;
import java.util.Map;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.util.DshmUtil;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;


public final class CacheClient {
	public static final  String delimiter = "#";
	public static final String CCS_APP_NAME = "ccs.appname";
	public static final String CCS_ZK_ADDRESS = "ccs.zk_address";
	public static final String PAAS_AUTH_URL = "paas.auth.url";
	public static final String PAAS_AUTH_PID = "paas.auth.pid";
	public static final String PAAS_CCS_SERVICEID = "paas.ccs.serviceid";
	public static final String PAAS_CCS_SERVICE_PASSWORD = "paas.ccs.servicepassword";
    private static ICacheClient cacheSdkClient = DshmUtil.getCacheClient();
	private static IDshmClient client;
	private static CacheClient cacheClient;
	
	private CacheClient(){}
	
	public static CacheClient getInstance(){
		if (cacheClient==null){
			cacheClient = new CacheClient();
		}
		return cacheClient;
	}
	
	public List<Map<String, String>> doQuery(String tableName, Map<String,String> params) throws OmcException{
		if(cacheClient == null){
			throw new OmcException("","cache client connection is null!");
		}
		if(client==null)
			client=new DshmClient();
		List<Map<String, String>> result = null;
		try {
			result = client.list(tableName).where(params).executeQuery(cacheSdkClient);
		} catch (Exception e) {
			throw new OmcException("CacheClient", e);
		}
		return result;
	}
}
