package com.ai.baas.amc.test.api.billdiscountquery.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.billdiscountquery.interfaces.IBillDiscountProductQuerySV;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductInfo;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryResponse;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryResponse;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfo;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfoQueryParam;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;


/**
 * 账单优惠产品查询服务<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BillDiscountProductQuerySVImplTest {
    
    private static final Log LOG = LogFactory.getLog(BillDiscountProductQuerySVImplTest.class);
    
    @Autowired
    private IBillDiscountProductQuerySV billDiscountProductQuerySV;

    @Test
    public void testQueryBillDiscountProductList() {
        BillDiscountProductListQueryRequest request = new BillDiscountProductListQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        //request.setProductId("");
        //request.setProductName("满赠");
        request.setDiscountType("bd");
        request.setEffectDate(DateUtil.getTimestamp("2015-12-04", DateUtil.DATE_FORMAT));
        request.setExpireDate(DateUtil.getTimestamp("2016-12-05", DateUtil.DATE_FORMAT));
        PageInfo<BillDiscountProductInfo> pageInfo = new PageInfo<BillDiscountProductInfo>();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(3);
        request.setPageInfo(pageInfo);
        LOG.info(JSON.toJSONString(request));
        BillDiscountProductListQueryResponse response = this.billDiscountProductQuerySV.queryBillDiscountProductList(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

    @Test
    public void testQueryBillDiscountProduct() {
        BillDiscountProductQueryRequest request = new BillDiscountProductQueryRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setProductId("203");
        LOG.info(JSON.toJSONString(request));
        BillDiscountProductQueryResponse response = this.billDiscountProductQuerySV.queryBillDiscountProduct(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    @Test
    public void getOweInfoListByPageInfo() {
        CustOweInfoQueryParam param = new CustOweInfoQueryParam();
        param.setTenantId("VIV-BYD");
        param.setCustName("asiainfo");
        //param.setBottomBalance(30000l);
        param.setTopBalance(120000l);
        List<CustOweInfo> info = MapperFactory.getAmcOweInfoMapper().getOweInfoListByPageInfo(param);
        LOG.info(JSON.toJSONString(info));
    }

    @Test
    public void getOweInfoCount() {
        CustOweInfoQueryParam param = new CustOweInfoQueryParam();
        param.setTenantId("VIV-BYD");
        param.setCustName("asiainfo");
        //param.setBottomBalance(30000l);
        param.setTopBalance(120000l);
        int count = MapperFactory.getAmcOweInfoMapper().getOweInfoCount(param);
        LOG.info(count);
    }
}
