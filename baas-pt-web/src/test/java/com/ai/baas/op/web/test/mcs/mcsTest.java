package com.ai.baas.op.web.test.mcs;

import static org.junit.Assert.assertEquals;

import com.ai.opt.sdk.appserver.LoadConfServiceStart;
import org.junit.Before;
import org.junit.Test;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class mcsTest {
    private ICacheClient cacheClient;

    private String namespace = "com.ai.opt.uni.session.sessionclient.baasptweb";

    @Before
    public void initData() {
        this.cacheClient = MCSClientFactory.getCacheClient(
                namespace);
    }
    //@Ignore
    @Test
    public void addCache() {
        cacheClient.set("testKey", "testValue");
        assertEquals("testValue", cacheClient.get("testKey"));
    }

    public static void main(String[] args) {
        LoadConfServiceStart.main(new String[]{"/Users/wangyongxin/ai_work/workspace/baas/baas-pt-web/src/main/resources/paas/sdkmode"});

    }
}
