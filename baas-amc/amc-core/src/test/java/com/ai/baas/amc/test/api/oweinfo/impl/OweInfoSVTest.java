package com.ai.baas.amc.test.api.oweinfo.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.oweinfo.params.OweInfoCreateRequest;
import com.ai.baas.amc.service.business.interfaces.IOweInfoBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OweInfoSVTest {
    @Autowired
    private transient IOweInfoBusiSV iOweInfoBusiSV;

    @Test
    public void createOweInfo() {
        OweInfoCreateRequest request = new OweInfoCreateRequest();
        request.setTenantId("test");
        request.setAcctId("1");
        request.setCustId("2");
        request.setCustName("3");
        iOweInfoBusiSV.createOweInfo(request);
    }
}
