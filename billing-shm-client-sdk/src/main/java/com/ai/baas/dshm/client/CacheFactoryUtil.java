package com.ai.baas.dshm.client;

import java.util.Properties;

import com.ai.opt.sdk.components.base.ComponentConfigLoader;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public final class CacheFactoryUtil {

    private CacheFactoryUtil() {
    }

    public static ICacheClient getCacheClient(Properties p,String namespace) {
    	
		ComponentConfigLoader.loadPaaSConf(p);
        return MCSClientFactory.getCacheClient(namespace);
    }
    public static ICacheClient getCacheClient(String namespace){
    	return MCSClientFactory.getCacheClient(namespace);
    }

}

