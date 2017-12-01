package com.ai.baas.smc.cache;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.dao.mapper.bo.StlPolicyItemCondition;
import com.ai.baas.smc.dao.mapper.bo.StlPolicyItemConditionCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

@Component
public class PolicyItemConditionCache extends AbstractCache {

    @Override
    public void write() throws Exception {
        // TODO Auto-generated method stub
        StlPolicyItemConditionCriteria stlPolicyItemConditionCriteria = new StlPolicyItemConditionCriteria();
        List<StlPolicyItemCondition> list = MapperFactory.getStlPolicyItemConditionMapper()
                .selectByExample(stlPolicyItemConditionCriteria);
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(SmcCacheConstant.NameSpace.POLICY_CACHE);
        cacheClient.hset(SmcCacheConstant.NameSpace.POLICY_CACHE,
                SmcCacheConstant.POLICY_ITEM_CONDITION, JSON.toJSONString(list));

    }

}
