package com.ai.baas.bmc.srv.flow.cache;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

@Component
public class CacheProxy {
	private static Logger logger = LoggerFactory.getLogger(CacheProxy.class);
	private static ICacheClient cacheClient;
	
	@PostConstruct
	public void init(){
		cacheClient = CacheFactoryUtil.getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
		logger.debug("缓存代理资源加载完成...");
	}
	
	public static ICacheClient getCacheClient(){
		return cacheClient;
	}
	
}
