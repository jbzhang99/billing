package com.ai.baas.amc.test.api.oweinfoquery.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.oweinfoquery.interfaces.IOweInfoQuerySV;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryResponse;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryResponse;
import com.ai.opt.base.vo.PageInfo;
import com.alibaba.fastjson.JSON;


/**
 * 欠费信息查询服务<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OweInfoQuerySVImplTest {
    
    private static final Log LOG = LogFactory.getLog(OweInfoQuerySVImplTest.class);
    
    @Autowired
    private IOweInfoQuerySV oweInfoQuerySV;

    @Test
    public void testQueryOweInfoList() {
        OweInfoListQueryRequest request = new OweInfoListQueryRequest();
        request.setTenantId("VIV-BYD");
        //request.setCustName("asia");
        //request.setCustGrade("A");
        request.setStartDate("201602");
        request.setEndDate("201607");
        //request.setBottomAmount(100.0);
        request.setTopAmount(150.0);
        PageInfo<OweInfo> pageInfo = new PageInfo<OweInfo>();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(1);
        request.setPageInfo(pageInfo);
        LOG.info(JSON.toJSONString(request));
        OweInfoListQueryResponse response = oweInfoQuerySV.queryOweInfoList(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

    @Test
    public void testQueryOweDetailInfo() {
        OweDetailInfoQueryRequest request = new OweDetailInfoQueryRequest();
        request.setTenantId("VIV-BYD");
        request.setCustId("10");
        LOG.info(JSON.toJSONString(request));
        OweDetailInfoQueryResponse response = oweInfoQuerySV.queryOweDetailInfo(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

}
