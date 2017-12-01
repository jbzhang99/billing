package com.ai.baas.amc.test.api.deposit.impl;


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

import com.ai.baas.amc.api.deposit.interfaces.IDepositSV;
import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.baas.amc.api.deposit.param.DepositResponse;
import com.ai.baas.amc.api.deposit.param.TransSummary;
import com.ai.baas.amc.test.api.fundquery.impl.FundQuerySVImplTest;
import com.alibaba.fastjson.JSON;

/**
 * 存款接口测试类
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DepositSVImplTest {
    
    private static final Log LOG = LogFactory.getLog(FundQuerySVImplTest.class);
    
    /**
     * 存款服务
     */
    @Autowired
    private IDepositSV depositSV;

    @Test
    public void testDepositFund() {
        DepositRequest request = new DepositRequest();
        request.setTenantId("VIV-BYD");
        request.setSystemId("baas-uac");
        request.setAccountId("214");
        request.setBusiDesc("存款10元");
        request.setBusiSerialNo("1166776887974574");
        request.setDepositTime("2017-01-25");
        request.setCustId("39");
        List<TransSummary> transSummaryList = new ArrayList<TransSummary>();
        TransSummary summary = new TransSummary();
        summary.setSubjectId(2004004);
        summary.setAmount(10l);
        //summary.setFundexpDate("2016-04-01 10:00:00");
        transSummaryList.add(summary);
        request.setTransSummary(transSummaryList);
        LOG.info(JSON.toJSONString(request));
        DepositResponse response = depositSV.depositFund(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);

    }

}
