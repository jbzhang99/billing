package com.ai.baas.amc.test.api.billdiscount.impl;



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

import com.ai.baas.amc.api.billdiscount.interfaces.IBillDiscountProductMaintainSV;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductDeleteRequest;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductMaintainResponse;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.baas.amc.api.billdiscount.param.GiftProduct;
import com.ai.opt.base.vo.BaseResponse;
import com.alibaba.fastjson.JSON;



/**
 * 账单优惠管理服务测试类
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BillDiscountProductMaintainSVImplTest {

    private static final Log LOG = LogFactory.getLog(BillDiscountProductMaintainSVImplTest.class);
    
    @Autowired
    private IBillDiscountProductMaintainSV billDiscountProductMaintainSV;
    
    /**
     * 满赠优惠
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testAddBillDiscountProductCase1() {
        BillDiscountProductVo vo = new BillDiscountProductVo();
        vo.setTradeSeq("4FA2FBBD1F6E49138E02ADF70D44FB4C2016040715360012");
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductName("满赠活动创建");
        vo.setDiscountType("mz");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setFullCostAmount(100.00);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        GiftProduct giftProduct = new GiftProduct();
        List<String> productIdList = new ArrayList<String>();
        productIdList.add("123123");
        productIdList.add("123124");
        giftProduct.setProductIdList(productIdList);
        giftProduct.setActiveMode("2");
        giftProduct.setActivePeriod("2");
        giftProduct.setEffectDate("2016-06-06 10:00:00");
        vo.setGiftProduct(giftProduct);
        LOG.info(JSON.toJSONString(vo));
        BillDiscountProductMaintainResponse response = this.billDiscountProductMaintainSV.addBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 满减优惠
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testAddBillDiscountProductCase2() {
        BillDiscountProductVo vo = new BillDiscountProductVo();
        vo.setTradeSeq("4FA2FBBD1F6E49138E02ADF70D44FB4C2016040715360013");
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductName("满减活动创建");
        vo.setDiscountType("mj");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setFullCostAmount(100.00);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        vo.setDiscountAmount(5);
        List<String> relatedSubjectList = new ArrayList<String>();
        relatedSubjectList.add("10000");
        vo.setRelatedSubjectList(relatedSubjectList);
        LOG.info(JSON.toJSONString(vo));
        BillDiscountProductMaintainResponse response = this.billDiscountProductMaintainSV.addBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 打折优惠
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testAddBillDiscountProductCase3() {
        BillDiscountProductVo vo = new BillDiscountProductVo();
        vo.setTradeSeq("4FA2FBBD1F6E49138E02ADF70D44FB4C2016040715360014");
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductName("限时打折优惠活动创建");
        vo.setDiscountType("dz");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        vo.setDiscountPercent(0.85);
        LOG.info(JSON.toJSONString(vo));
        BillDiscountProductMaintainResponse response = this.billDiscountProductMaintainSV.addBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 保底优惠
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testAddBillDiscountProductCase4() {
        BillDiscountProductVo vo = new BillDiscountProductVo();
        vo.setTradeSeq("4FA2FBBD1F6E49138E02ADF70D44FB4C2016040715360015");
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductName("保底优惠活动创建");
        vo.setDiscountType("bd");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setBottomAmount(200);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        List<String> relatedSubjectList = new ArrayList<String>();
        relatedSubjectList.add("10000");
        vo.setRelatedSubjectList(relatedSubjectList);
        LOG.info(JSON.toJSONString(vo));
        BillDiscountProductMaintainResponse response = this.billDiscountProductMaintainSV.addBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 封顶优惠
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testAddBillDiscountProductCase5() {
        BillDiscountProductVo vo = new BillDiscountProductVo();
        vo.setTradeSeq("4FA2FBBD1F6E49138E02ADF70D44FB4C2016040715360016");
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductName("封顶优惠活动创建");
        vo.setDiscountType("fd");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setTopAmount(1000);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        List<String> relatedSubjectList = new ArrayList<String>();
        relatedSubjectList.add("10000");
        vo.setRelatedSubjectList(relatedSubjectList);
        LOG.info(JSON.toJSONString(vo));
        BillDiscountProductMaintainResponse response = this.billDiscountProductMaintainSV.addBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

    @Test
    public void testDeleteBillDiscountProduct() {
        BillDiscountProductDeleteRequest request = new BillDiscountProductDeleteRequest();
        request.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        request.setProductId("162");
        LOG.info(JSON.toJSONString(request));
        BaseResponse response = this.billDiscountProductMaintainSV.deleteBillDiscountProduct(request);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 满赠优惠(变更操作)
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testUpdateBillDiscountProductCase1() {
        BillDiscountProductUpdateVo vo = new BillDiscountProductUpdateVo();
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductId("181");
        vo.setProductName("满赠活动创建名称修改");
        vo.setStatus("1");
        vo.setEffectDate("2013-04-01 10:00:00");
        vo.setExpireDate("2019-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setFullCostAmount(100.00);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("50000");
        billSubjectList.add("21001");
        vo.setBillSubjectList(billSubjectList);
        GiftProduct giftProduct = new GiftProduct();
        List<String> productIdList = new ArrayList<String>();
        productIdList.add("123123");
        productIdList.add("123124");
        giftProduct.setProductIdList(productIdList);
        giftProduct.setActiveMode("2");
        giftProduct.setActivePeriod("2");
        giftProduct.setEffectDate("2019-06-06 10:00:00");
        vo.setGiftProduct(giftProduct);
        LOG.info(JSON.toJSONString(vo));
        BaseResponse response = this.billDiscountProductMaintainSV.updateBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 满减优惠(变更操作)
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testUpdateBillDiscountProductCase2() {
        BillDiscountProductUpdateVo vo = new BillDiscountProductUpdateVo();
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductId("");
        vo.setProductName("满减活动创建");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setFullCostAmount(100.00);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        vo.setDiscountAmount(5.0);
        List<String> relatedSubjectList = new ArrayList<String>();
        relatedSubjectList.add("10000");
        vo.setRelatedSubjectList(relatedSubjectList);
        LOG.info(JSON.toJSONString(vo));
        BaseResponse response = this.billDiscountProductMaintainSV.updateBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 打折优惠(变更操作)
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testUpdateBillDiscountProductCase3() {
        BillDiscountProductUpdateVo vo = new BillDiscountProductUpdateVo();
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductId("");
        vo.setProductName("限时打折优惠活动创建");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        vo.setDiscountPercent(0.85);
        LOG.info(JSON.toJSONString(vo));
        BaseResponse response = this.billDiscountProductMaintainSV.updateBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 保底优惠(变更操作)
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testUpdateBillDiscountProductCase4() {
        BillDiscountProductUpdateVo vo = new BillDiscountProductUpdateVo();
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductId("");
        vo.setProductName("保底优惠活动创建");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setBottomAmount(200.0);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        List<String> relatedSubjectList = new ArrayList<String>();
        relatedSubjectList.add("10000");
        vo.setRelatedSubjectList(relatedSubjectList);
        LOG.info(JSON.toJSONString(vo));
        BaseResponse response = this.billDiscountProductMaintainSV.updateBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }
    
    /**
     * 封顶优惠(变更操作)
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    @Test
    public void testUpdateBillDiscountProductCase5() {
        BillDiscountProductUpdateVo vo = new BillDiscountProductUpdateVo();
        vo.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        vo.setProductName("封顶优惠活动创建");
        vo.setEffectDate("2016-04-01 10:00:00");
        vo.setExpireDate("2016-05-01 10:00:00");
        vo.setRemark("ceshi");
        vo.setTopAmount(1000.00);
        List<String> billSubjectList = new ArrayList<String>();
        billSubjectList.add("10000");
        billSubjectList.add("20301");
        vo.setBillSubjectList(billSubjectList);
        List<String> relatedSubjectList = new ArrayList<String>();
        relatedSubjectList.add("10000");
        vo.setRelatedSubjectList(relatedSubjectList);
        LOG.info(JSON.toJSONString(vo));
        BaseResponse response = this.billDiscountProductMaintainSV.updateBillDiscountProduct(vo);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

}
