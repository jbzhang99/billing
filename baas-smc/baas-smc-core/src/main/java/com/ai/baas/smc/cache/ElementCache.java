package com.ai.baas.smc.cache;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.constants.SmcConstants;
import com.ai.baas.smc.dao.mapper.bo.StlElement;
import com.ai.baas.smc.dao.mapper.bo.StlElementCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

@Component
public class ElementCache extends AbstractCache {

    @Override
    public void write() throws Exception {
        StlElementCriteria stlElementCriteria = new StlElementCriteria();
        stlElementCriteria.createCriteria().andStateEqualTo(SmcConstants.StlElement.State.NORMAL);
        List<StlElement> stlElements = MapperFactory.getElementMapper().selectByExample(
                stlElementCriteria);
        if (CollectionUtil.isEmpty(stlElements)) {
            return;
        }
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(SmcCacheConstant.NameSpace.ELEMENT_CACHE);
        for (StlElement stlElement : stlElements) {
            // key:tenantId.elementId,value:StlElement
            StringBuilder key = new StringBuilder();
            key.append(stlElement.getTenantId()).append(".").append(stlElement.getElementId());
            cacheClient.hset(SmcCacheConstant.NameSpace.ELEMENT_CACHE, key.toString(),
                    JSON.toJSONString(stlElement));
        }
    }

}
