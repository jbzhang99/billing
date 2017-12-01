package com.ai.baas.smc.test.api.test;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.smc.service.busi.interfaces.ITestBusiSV;
import com.ai.opt.sdk.components.base.ComponentConfigLoader;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class TestSV {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void test() {
        ctx.getBean(ITestBusiSV.class).test();
    }

    // cacheClient = CacheClientFactory.getCacheClient(NameSpace.OBJECT_ELEMENT_CACHE);
    @Test
    public void cache() {

        // ICacheClient cacheClient = CacheClientFactory
        // .getCacheClient("com.ai.baas.smc.cache.PolicyToElementCache");
        // String elementStrings = cacheClient.hget("com.ai.baas.smc.cache.PolicyToElementCache",
        // "MVNE_93000");
        // // Long num= cacheClient.hdel("com.ai.baas.smc.cache.ObjectToElementCache", "MVNE" + "."
        // +
        // // "msg");
        // // System.out.print(num);
        // List<StlElement> list = JSON.parseArray(elementStrings, StlElement.class);
        // System.out.print(elementStrings);
        //
        // ICacheClient cacheClient = CacheClientFactory.getCacheClient("stats_times");
        // String key = "busidata_tenantId_batchNo_billTimeSn_objectId_stats_times";

        // List<FinishListVo> finishListVos = new ArrayList<FinishListVo>();
        // FinishListVo finishListVo = new FinishListVo();
        // finishListVo.setBatchNo("20160406");
        // finishListVo.setBillTimeSn("JSMVNE2016030100123hx");
        // finishListVo.setBusidata("busidata");
        // finishListVo.setObjectId("MSG");
        // finishListVo.setTenantId("MVNE");
        // finishListVo.setStats_times("4");
        // finishListVos.add(finishListVo);
        // finishListVos.add(finishListVo);
        // System.out.println(JSON.toJSONString(finishListVos));
        // cacheClient.set( key, JSON.toJSONString(finishListVos));

        // String aString = cacheClient.get(key);
        // System.out.print(aString);
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient("com.ai.baas.smc.cache.check.count");
        String key = "busidata_MVNE_20150401001_stats_times";
        long aString = cacheClient.del(key);
        System.out.print(aString);
        // MVNEchnnel_id
    }

    // busidata_MVNE_20150401001_stats_times busidata_MVNE_20150401001_stats_times
    @Test
    public void cacheC() {
        // ICacheClient cacheClient = CacheClientFactory
        // .getCacheClient("com.ai.baas.smc.cache.check.count");
        // String a = cacheClient.get("busiData_MVNE_20220401001_verify_success");
        // System.out.print(a);
        // ICacheClient cacheClient = MCSClientFactory.getCacheClient("stats_times");
        // String a = cacheClient.get("busidata_tenantId_batchNo_billTimeSn_objectId_stats_times");
        // System.out.print(a);
        IDshmClient dshmClient = new DshmClient();
        Properties p = new Properties();
        p.setProperty("paas.auth.url",
                "http://10.1.245.4:19811/service-portal-uac-web/service/auth");
        p.setProperty("paas.auth.pid", "87EA5A771D9647F1B5EBB600812E3067");
        p.setProperty("paas.ccs.serviceid", "CCS008");
        p.setProperty("paas.ccs.servicepassword", "123456");
        ComponentConfigLoader.loadPaaSConf(p);
        ICacheClient calParamCacheClient = MCSClientFactory
                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("tenant_id", "BIU");
        params.put("batch_no", "SF20160401033");
        List<Map<String, String>> results = dshmClient.list("stl_import_log").where(params)
                .executeQuery(calParamCacheClient);

        Map<String, String> map = results.get(0);
        String objectId = map.get("object_id");
        String billTimeSn = map.get("bill_time_sn");
    }

    @Test
    public void cacheA() {
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient("com.ai.baas.smc.cache.check.count");
        String a = cacheClient.hget("com.ai.baas.smc.cache.check.count",
                "busidata_MVNE_20150401001_verify_success");
        System.out.print(a);
    }

    @Test
    public void jsonToObject() {
        // Map<String, Integer> map = new HashMap<String, Integer>();
        // map.put("1.101", 1);
        // System.out.print(map.containsKey("1.101"));
        // StringBuilder sbBuilder = new StringBuilder();
        // sbBuilder.append("MVNE");
        // sbBuilder.append(".");
        // sbBuilder.append("93000");

        String aString = "MVNE_93000_a";
        String[] a = aString.split("_");
        String bString = a[1];
        System.out.print(bString);
    }
}
