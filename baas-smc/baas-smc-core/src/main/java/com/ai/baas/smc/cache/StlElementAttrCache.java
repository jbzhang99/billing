package com.ai.baas.smc.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.dao.mapper.bo.StlElementAttr;
import com.ai.baas.smc.dao.mapper.bo.StlElementAttrCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.baas.smc.dao.mapper.interfaces.StlElementAttrMapper;
import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

@Component
public class StlElementAttrCache extends AbstractCache {
private static final Logger LOGGER = LogManager.getLogger(StlElementAttrCache.class);
    @Override
    public void write() throws Exception {
        StlElementAttrCriteria stlElementAttrCriteria = new StlElementAttrCriteria();
        StlElementAttrMapper mapper = MapperFactory.getElementAttrMapper();
        List<StlElementAttr> stlElementAttrs = mapper.selectByExample(stlElementAttrCriteria);
        if (CollectionUtil.isEmpty(stlElementAttrs)) {
            return;
        }

        List<StlElementAttr> stlElementAttrsUniques = uniqueStlElement(stlElementAttrs);

        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(SmcCacheConstant.NameSpace.STL_ELEMENT_ATTR_CACHE);

        for (StlElementAttr stlElementAttr : stlElementAttrsUniques) {
            StlElementAttrCriteria stlElementAttrCriteriaNew = new StlElementAttrCriteria();
            StlElementAttrCriteria.Criteria criteriaNew = stlElementAttrCriteriaNew
                    .createCriteria();
            criteriaNew.andTenantIdEqualTo(stlElementAttr.getTenantId());
            criteriaNew.andElementIdEqualTo(stlElementAttr.getElementId());
            criteriaNew.andSubObjectIdEqualTo(stlElementAttr.getSubObjectId());

            List<StlElementAttr> stlElementAttrs2 = mapper
                    .selectByExample(stlElementAttrCriteriaNew);

            if (stlElementAttrs2.size() != 0) {
                StringBuilder key = new StringBuilder();
                key.append(stlElementAttr.getTenantId());
                key.append("_");
                key.append(stlElementAttr.getElementId());
                key.append("_");
                key.append(stlElementAttr.getSubObjectId());
                LOGGER.info("key:" + key.toString() + "value:"
                        + JSON.toJSONString(stlElementAttrs2));
                cacheClient.hset(SmcCacheConstant.NameSpace.STL_ELEMENT_ATTR_CACHE, key.toString(),
                        JSON.toJSONString(stlElementAttrs2));
            }
        }
    }

    private List<StlElementAttr> uniqueStlElement(List<StlElementAttr> stlElementAttrs) {
        List<StlElementAttr> results = new ArrayList<StlElementAttr>();
        Map<String, Integer> unique = new HashMap<String, Integer>();
        for (StlElementAttr stlElementAttr : stlElementAttrs) {
            if (unique.containsKey(stlElementAttr.getElementId().toString()
                    + stlElementAttr.getTenantId() + stlElementAttr.getSubObjectId())) {
                continue;
            } else {
                unique.put(stlElementAttr.getElementId().toString() + stlElementAttr.getTenantId()
                        + stlElementAttr.getSubObjectId(), 1);
                results.add(stlElementAttr);
            }
        }
        return results;
    }

}
