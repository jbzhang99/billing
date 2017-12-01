package com.ai.baas.amc.test.api.deduct.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.deduct.interfaces.IDeductSV;
import com.ai.baas.amc.api.deduct.param.DeductRequest;
import com.ai.baas.amc.api.deduct.param.DeductResponse;
import com.ai.baas.amc.api.deduct.param.TransSummary;
import com.alibaba.fastjson.JSON;



/**
 * 扣款接口测试类
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DeductSVImplTest {

    private static final Log LOG = LogFactory.getLog(DeductSVImplTest.class);

    @Autowired
    private IDeductSV deductSV;
    
    @Test
    public void testDeductFund() {
        DeductRequest request = new DeductRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setSystemId("baas-uac");
        request.setAccountId("751");
        request.setBusinessCode("10091");
        request.setExternalId("11667724579792");
        request.setTotalAmount(10000l);
        List<TransSummary> transSummaryList = new ArrayList<TransSummary>();
        TransSummary summary = new TransSummary();
        summary.setSubjectId(100000l);
        //summary.setBookId(1);
        summary.setAmount(10000l);
        transSummaryList.add(summary);
        request.setTransSummary(transSummaryList);
        LOG.info(JSON.toJSONString(request));
        DeductResponse response = deductSV.deductFund(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
}
