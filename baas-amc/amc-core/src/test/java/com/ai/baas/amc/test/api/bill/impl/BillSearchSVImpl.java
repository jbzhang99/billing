package com.ai.baas.amc.test.api.bill.impl;

import com.ai.baas.amc.api.bill.interfaces.IBillSearchSV;
import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.api.bill.params.BillSearchResponse;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BillSearchSVImpl {

    @Autowired
    private IBillSearchSV billSearchSV;

    @Test
    public void billSearch(){
        BillSearchRequest vo = new BillSearchRequest();
        vo.setTenantId("ECITIC");
        vo.setBillMonth("201611");
        vo.setUserType("0");
        vo.setPageNo(1);
        vo.setPageSize(5);
        BillSearchResponse billSearchResponse = billSearchSV.queryBillList(vo);
        System.out.println(JSON.toJSONString(billSearchResponse));
    }
}
