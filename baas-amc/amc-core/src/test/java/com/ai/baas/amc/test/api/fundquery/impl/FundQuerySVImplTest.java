package com.ai.baas.amc.test.api.fundquery.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.fundquery.interfaces.IFundQuerySV;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.amc.api.fundquery.param.SpecialFundBookQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;

/**
 * 账户资金查询接口测试类
 *
 * Date: 2016年3月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class FundQuerySVImplTest {
    
    private static final Log LOG = LogFactory.getLog(FundQuerySVImplTest.class);

    @Autowired
    private IFundQuerySV fundQuerySV;
    
    @Test
    public void testQueryFund() {
        FundBookQueryRequest request = new FundBookQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setAccountId("751");
        LOG.info(JSON.toJSONString(request));
        FundBookQueryResponse response = this.fundQuerySV.queryFund(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

    @Test
    public void testQueryUsableFund() {
        FundBookQueryRequest request = new FundBookQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setAccountId("751");
        LOG.info(JSON.toJSONString(request));
        FundBookQueryResponse response = this.fundQuerySV.queryUsableFund(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

    @Test
    public void testQueryFundBySubjectId() {
        SpecialFundBookQueryRequest request = new SpecialFundBookQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setAccountId("751");
        request.setSubjectId(100000l);
        LOG.info(JSON.toJSONString(request));
        FundBookQueryResponse response = this.fundQuerySV.queryFundBySubjectId(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    @Test(expected = BusinessException.class)
    public void testQueryFundThrowExcepiton() {
        FundBookQueryRequest request = new FundBookQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        //request.setAccountId("751");
        LOG.info(JSON.toJSONString(request));
        FundBookQueryResponse response = this.fundQuerySV.queryFund(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

}
