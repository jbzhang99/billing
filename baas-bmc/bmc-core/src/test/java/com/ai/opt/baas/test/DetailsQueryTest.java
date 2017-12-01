package com.ai.opt.baas.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.detailsquery.interfaces.IDetailsQuerySV;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/context/core-context.xml"})
public class DetailsQueryTest {

    @Autowired
    private IDetailsQuerySV detailsQuerySV;

    @Test
    public void queryDetails(){
        DetailsQueryRequest request = new DetailsQueryRequest();
        request.setTenantId("ECITIC");
        request.setStartDate("20161001000000");
        request.setEndDate("20161031000000");
        request.setServiceType("ecs");
        DetailsQueryResponse response = detailsQuerySV.queryDetails(request);
        System.out.println(JSON.toJSONString(response));
    }
}
