package com.ai.baas.smc.cache;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.constants.SmcConstants;
import com.ai.baas.smc.dao.mapper.bo.StlPolicy;
import com.ai.baas.smc.dao.mapper.bo.StlPolicyCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

@Component
public class PolicyCache extends AbstractCache {

    @Override
    public void write() throws Exception {
        StlPolicyCriteria criteria = new StlPolicyCriteria();
        criteria.createCriteria().andStateEqualTo(SmcConstants.StlPolicy.State.NORMAL);
        List<StlPolicy> stlPolicies = MapperFactory.getStlPolicyMapper().selectByExample(criteria);
        if (CollectionUtil.isEmpty(stlPolicies)) {
            return;
        }
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(SmcCacheConstant.NameSpace.POLICY_CACHE);
        for (StlPolicy stlPolicy : stlPolicies) {
            // key:tenantId.policyCode,value:stlPolicy
            StringBuilder keyOne = new StringBuilder();
            keyOne.append(stlPolicy.getTenantId());
            keyOne.append(".");
            keyOne.append(stlPolicy.getPolicyCode());
            cacheClient.hset(SmcCacheConstant.NameSpace.POLICY_CACHE, keyOne.toString(),
                    JSON.toJSONString(stlPolicy));
        }
        cacheClient.hset(SmcCacheConstant.NameSpace.POLICY_CACHE, SmcCacheConstant.POLICY_ALL,
                JSON.toJSONString(stlPolicies));
    }

}
