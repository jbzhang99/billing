package com.ai.baas.smc.test.api.queryBillData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.billdata.param.QueryBillDataDetailRequest;
import com.ai.baas.smc.api.billdata.param.QueryBillDataRequest;
import com.ai.baas.smc.api.billdata.param.QueryBillDataResponse;
import com.ai.baas.smc.api.billdata.param.QueryBillDetailResponse;
import com.ai.baas.smc.service.busi.interfaces.IBillDataBusiSV;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class StlBillDataTest {

    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void query() {
        IBillDataBusiSV iBillDataBusiSV = ctx.getBean(IBillDataBusiSV.class);
        QueryBillDataRequest queryBillDataRequest = new QueryBillDataRequest();
        queryBillDataRequest.setBillTimeSn("20160303");
        queryBillDataRequest.setTenantId("baas-1");
        String string = JSON.toJSONString(queryBillDataRequest);
        System.out.print(string);
        QueryBillDataResponse queryBillDataResponse = iBillDataBusiSV
                .queryBillData(queryBillDataRequest);
        System.out.println(queryBillDataResponse);
    }

    @Test
    public void queryHbase() {
        IBillDataBusiSV iBillDataBusiSV = ctx.getBean(IBillDataBusiSV.class);
        QueryBillDataDetailRequest queryBillDataDetailRequest = new QueryBillDataDetailRequest();
//        {
//            "billId": 2264,
//            "billTimeSn": "201604",
//            "tenantId": "BIU"
//          }

        queryBillDataDetailRequest.setBillId(2264l);
        queryBillDataDetailRequest.setTenantId("BIU");
        queryBillDataDetailRequest.setBillTimeSn("201609");
        String string = JSON.toJSONString(queryBillDataDetailRequest);
        System.out.println(string);
        QueryBillDetailResponse queryBillDetailResponse = iBillDataBusiSV
                .queryBillDataDetail(queryBillDataDetailRequest);
        System.out.println(queryBillDetailResponse);
    }

}
