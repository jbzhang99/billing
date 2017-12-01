package com.ai.opt.baas.bmc.test.api.citic;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.citic.interfaces.IOrderChangeSV;
import com.ai.baas.bmc.api.citic.params.InstanceUpdateInfo;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OrderinfoChangeSVTest {

    @Autowired 
    IOrderChangeSV iOrderChangeSV;
    @Test
    public void testSthElse(){
        InstanceUpdateInfo ins = new InstanceUpdateInfo();
        
        ins.setChange_code("REMOVE");
        ins.setInstance_id("11a6e906-e3dc-4e97-b079-f9c8ea5330d2");
        ins.setTenant_id("CITIC");
        ins.setTrade_seq("aaaaaaa");
        System.out.println(JSON.toJSONString(ins));
        System.out.println(JSON.toJSONString(iOrderChangeSV.instanceChange(ins)));
        
        String s = HttpClientUtil.sendPost(
                "http://10.1.130.84:10884/baas-bmc/citic/instanceChange",
                JSON.toJSONString(ins));
    }
}
