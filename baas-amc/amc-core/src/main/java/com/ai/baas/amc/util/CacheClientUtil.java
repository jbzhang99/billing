package com.ai.baas.amc.util;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

import java.util.List;
import java.util.Map;

/**
 * 共享内存调用
 */
public final class CacheClientUtil {
	public static final  String delimiter = "#";

	private static ICacheClient cacheSdkClient;
	private static IDshmClient client;
	private static CacheClientUtil cacheClient;
	
	private CacheClientUtil(){}
	
	public static CacheClientUtil getInstance(){
		if (cacheClient==null){
			cacheClient = new CacheClientUtil();
		}
		if (cacheSdkClient==null){
			cacheSdkClient = CacheFactoryUtil
					.getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
		}
		return cacheClient;
	}
	
	public List<Map<String, String>> doQuery(String tableName, Map<String,String> params)
			throws BusinessException,SystemException{
		if(cacheClient == null){
			throw new BusinessException("","cache client connection is null!");
		}
		if(client==null){
			client=new DshmClient();}
		List<Map<String, String>> result = null;
		try {
			result = client.list(tableName).where(params).executeQuery(cacheSdkClient);
		} catch (Exception e) {
			throw new SystemException("CacheClient", e);
		}
		return result;
	}
}
