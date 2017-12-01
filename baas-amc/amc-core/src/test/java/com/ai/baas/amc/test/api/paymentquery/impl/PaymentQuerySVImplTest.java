package com.ai.baas.amc.test.api.paymentquery.impl;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.paymentquery.interfaces.IPaymentQuerySV;
import com.ai.baas.amc.api.paymentquery.param.PaymentLog;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;

/**
 * 缴费记录查询服务<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class PaymentQuerySVImplTest {

    private static final Log LOG = LogFactory.getLog(PaymentQuerySVImplTest.class);
    
    @Autowired
    private IPaymentQuerySV paymentQuerySV;
    
    @Test
    public void testQueryPaymentLog() {
        PaymentLogQueryRequest request = new PaymentLogQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setCustName("近平");
        request.setCustGrade("A");
        //request.setPaySerialCode("641");
        Timestamp sysDate = DateUtil.getSysDate();
        request.setStartTime(DateUtil.getTimeThisMonthFirstSec(sysDate));
        request.setEndTime(DateUtil.getTimeThisMonthLastSec(sysDate));
        request.setBottomAmount(10.0);
        request.setTopAmount(100.0);
        PageInfo<PaymentLog> pageInfo = new PageInfo<PaymentLog>();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(1);
        request.setPageInfo(pageInfo);
        LOG.info(JSON.toJSONString(request));
        PaymentLogQueryResponse response = this.paymentQuerySV.queryPaymentLog(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

}
