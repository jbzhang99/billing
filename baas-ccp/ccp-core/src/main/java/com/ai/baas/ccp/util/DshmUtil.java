package com.ai.baas.ccp.util;

import com.ai.baas.dshm.api.dshmprocess.interfaces.IdshmSV;
import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class DshmUtil {
    //dubbo服务
    private static IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);

    private static IDshmClient client = new DshmClient();
    //原生cache
    private static ICacheClient cacheClient = CacheFactoryUtil.getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);


    private DshmUtil() {
    }

    public static IdshmSV getIdshmSV() {
        return idshmSV;
    }
    
    public static IDshmClient getDshmClient(){
        return client;
    }
    
    public static ICacheClient getCacheClient(){
        return cacheClient;
    }
}
