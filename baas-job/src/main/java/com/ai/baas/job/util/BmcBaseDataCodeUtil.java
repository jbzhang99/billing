package com.ai.baas.job.util;

import java.util.Collections;
import java.util.List;

import com.ai.baas.job.constants.BaasJobCacheConstants;
import com.ai.baas.job.dao.mapper.bo.BmcBasedataCode;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

public final class BmcBaseDataCodeUtil {
    private BmcBaseDataCodeUtil(){}

    public static List<BmcBasedataCode> getBmcBasedataCodes(String tenantId, String paramType) {
        String key = tenantId + "." + paramType;
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(BaasJobCacheConstants.NameSpace.BMC_BASEDATA_CODE);
        String conf = cacheClient.hget(BaasJobCacheConstants.NameSpace.BMC_BASEDATA_CODE, key);
        if(StringUtil.isBlank(conf)){
            key = "PUB." + paramType;
            conf = cacheClient.hget(BaasJobCacheConstants.NameSpace.BMC_BASEDATA_CODE, key);
        }
        if(StringUtil.isBlank(conf)){
            return Collections.emptyList();
        }
        return JSON.parseArray(conf, BmcBasedataCode.class);
    }
    
    public static BmcBasedataCode getBmcBasedataCode(String tenantId, String paramType, String paramCode) {
        String key = tenantId + "." + paramType + "." + paramCode;
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(BaasJobCacheConstants.NameSpace.BMC_BASEDATA_CODE);
        String conf = cacheClient.hget(BaasJobCacheConstants.NameSpace.BMC_BASEDATA_CODE, key);
        if(StringUtil.isBlank(conf)){
            key = "PUB." + paramType + "." + paramCode;
            conf = cacheClient.hget(BaasJobCacheConstants.NameSpace.BMC_BASEDATA_CODE, key);
        }
        if(StringUtil.isBlank(conf)){
            return null;
        }
        return JSON.parseObject(conf, BmcBasedataCode.class);
    }
    
    public static String getBmcBasedataCodeDefaultValue(String tenantId, String paramType, String paramCode){
        BmcBasedataCode basedataCode = getBmcBasedataCode(tenantId, paramType, paramCode);
        if(null == basedataCode){
            return "";
        }
        return basedataCode.getDefaultValue();
    }
}
