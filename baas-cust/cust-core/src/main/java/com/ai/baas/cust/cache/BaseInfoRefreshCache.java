package com.ai.baas.cust.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.cust.constants.CacheRSMapper;
import com.ai.baas.cust.dao.interfaces.BmcBasedataCodeMapper;
import com.ai.baas.cust.dao.mapper.bo.BmcBasedataCode;
import com.ai.baas.cust.dao.mapper.bo.BmcBasedataCodeCriteria;
import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 刷新缓存类
 * @author wangluyang
 *
 */
@Component
public class BaseInfoRefreshCache extends AbstractCache {

	private static final Logger LOG = LogManager.getLogger(BaseInfoCache.class);
	
	@Autowired
	private BmcBasedataCodeMapper bmcBasedataCodeMapper;
	
	@Override
	public void write() throws Exception {
		// TODO Auto-generated method stub
		List<BmcBasedataCode> list = bmcBasedataCodeMapper.selectByExample(new BmcBasedataCodeCriteria());
		if(CollectionUtil.isEmpty(list)){
			return;
		}
		ICacheClient cacheClient = MCSClientFactory.getCacheClient(CacheRSMapper.CACHE_BASEINFO);
		
		//清空上次缓存的数据
		cacheClient.del(CacheRSMapper.CACHE_BASEINFO);
		// key = #tenantId.#paramType
		for(BmcBasedataCode vo:list){
			LOG.debug("缓存BaseInfo信息:租户{}paramType{}paramCode{}", vo.getTenantId(), vo.getParamType(), vo.getParamCode());
			List<BmcBasedataCode> rsList = new ArrayList<>();
			String data = cacheClient.hget(CacheRSMapper.CACHE_BASEINFO,
					generateBaseInfoKey(vo.getTenantId(), vo.getParamType()));
			if(data!=null){
				rsList = (List<BmcBasedataCode>) JSONObject.parseArray(data, BmcBasedataCode.class);
			}
			rsList.add(vo);
			cacheClient.hset(CacheRSMapper.CACHE_BASEINFO, generateBaseInfoKey(vo.getTenantId(), vo.getParamType()), JSON.toJSONString(rsList));
		}
		// key = #tenantId.#paramType.#paramCode
		for(BmcBasedataCode vo:list){
		    String key = new StringBuilder().append(vo.getTenantId()).append(".").append(vo.getParamType()).append(".").append(vo.getParamCode()).toString();
		    cacheClient.hset(CacheRSMapper.CACHE_BASEINFO, key, JSON.toJSONString(vo));
		}
	}
	
	public static String generateBaseInfoKey(String tenantId, String paramType){
		return tenantId.toUpperCase() + "." + paramType.toUpperCase();
	}
}
