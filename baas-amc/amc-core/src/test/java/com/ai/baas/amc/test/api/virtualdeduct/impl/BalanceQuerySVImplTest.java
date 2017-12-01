package com.ai.baas.amc.test.api.virtualdeduct.impl;

import com.ai.baas.amc.api.virtualdeduct.interfaces.IBalanceQuerySV;
import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.baas.amc.constants.OwnerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 模拟冲销测试类
 *
 * Created by liutong5 on 16/4/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BalanceQuerySVImplTest {
    @Autowired
    IBalanceQuerySV balanceQuerySV;

    @Test
    public void cancelAccountProcessTest(){
        BalanceQueryRequest queryRequest = new BalanceQueryRequest();
        queryRequest.setOwerId("1234");
        queryRequest.setTenantId("TR");
        //目前只支持cash
        queryRequest.setBusinessCode("cash");
        queryRequest.setOwerType(OwnerType.ACC_TYPE);
        queryRequest.setProductType("VIV");
        VdRealTimeBalance balance = balanceQuerySV.cancelAccountProcess(queryRequest);
        System.out.println(balance.toString());
    }
}
