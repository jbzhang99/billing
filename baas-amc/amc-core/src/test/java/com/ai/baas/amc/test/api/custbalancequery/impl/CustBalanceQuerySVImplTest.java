package com.ai.baas.amc.test.api.custbalancequery.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.custbalancequery.interfaces.ICustBalanceQuerySV;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryResponse;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceVo;
import com.ai.opt.base.vo.PageInfo;
import com.alibaba.fastjson.JSON;

/**
 * 可用余额查询服务（客户级别）<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CustBalanceQuerySVImplTest {
    
    private static final Log LOG = LogFactory.getLog(CustBalanceQuerySVImplTest.class);

    @Autowired
    private ICustBalanceQuerySV custBalanceQuerySV;
    
    @Test
    public void testQueryUsableBalance() {
        UsableBalanceQueryRequest request = new UsableBalanceQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setCustName("近平");
        request.setCustGrade("A");
        PageInfo<UsableBalanceVo> pageInfo = new PageInfo<UsableBalanceVo>();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(1);
        request.setPageInfo(pageInfo);
        LOG.info(JSON.toJSONString(request));
        UsableBalanceQueryResponse response = custBalanceQuerySV.queryUsableBalance(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

}
