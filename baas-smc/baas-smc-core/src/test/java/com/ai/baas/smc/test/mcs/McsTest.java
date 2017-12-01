package com.ai.baas.smc.test.mcs;

import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

public class McsTest {
    public static void main(String[] args) {

        String nameSpace = SmcCacheConstant.NameSpace.SYS_PARAM_CACHE;
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(nameSpace);
        String key = "test";
        String value = "testtest";
//        cacheClient.hset(nameSpace, key, value);
        System.out.println(JSON.toJSONString(cacheClient.hget(nameSpace, key)));
        System.out.println(JSON.toJSONString(cacheClient.hget(nameSpace, key)));
        System.out.println(JSON.toJSONString(cacheClient.hget(nameSpace, key)));
        System.out.println(JSON.toJSONString(cacheClient.hget(nameSpace, key)));
        System.out.println(JSON.toJSONString(cacheClient.hget(nameSpace, key)));
    }
}
