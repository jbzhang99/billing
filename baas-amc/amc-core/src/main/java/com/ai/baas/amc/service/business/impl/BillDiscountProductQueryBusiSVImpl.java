package com.ai.baas.amc.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductInfo;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.ExtendInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcProductDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfo;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductDetailAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductExtAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductInfoAtomSV;
import com.ai.baas.amc.service.business.interfaces.IBillDiscountProductQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠活动查询服务实现类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class BillDiscountProductQueryBusiSVImpl implements IBillDiscountProductQueryBusiSV {
        
    @Autowired
    private IAmcProductInfoAtomSV amcProductInfoAtomSV;
    
    @Autowired
    private IAmcProductDetailAtomSV amcProductDetailAtomSV;
    
    @Autowired
    private IAmcProductExtAtomSV amcProductExtAtomSV;

    @Override
    public PageInfo<BillDiscountProductInfo> queryBillDiscountProductList(
            BillDiscountProductListQueryRequest request) throws BusinessException {
        PageInfo<AmcProductInfo> amcProductPageInfo = amcProductInfoAtomSV.queryAmcProductInfoList(request);
        PageInfo<BillDiscountProductInfo> pageInfo = new PageInfo<BillDiscountProductInfo>();
        List<BillDiscountProductInfo> infoList = new ArrayList<BillDiscountProductInfo>();
        if(!CollectionUtil.isEmpty(amcProductPageInfo.getResult())) {
            for(AmcProductInfo amcProductInfo : amcProductPageInfo.getResult()) {
                BillDiscountProductInfo info = this.assemableBillDiscountProductInfo(amcProductInfo);
                infoList.add(info);
            }
        }
        
        pageInfo.setCount(amcProductPageInfo.getCount());
        pageInfo.setPageNo(amcProductPageInfo.getPageNo());
        pageInfo.setPageSize(amcProductPageInfo.getPageSize());
        pageInfo.setResult(infoList);
        return pageInfo;
    }
    
    /**
     * 组装账单优惠产品信息
     * @param amcProductInfo
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private BillDiscountProductInfo assemableBillDiscountProductInfo(AmcProductInfo amcProductInfo) {
        BillDiscountProductInfo info = new BillDiscountProductInfo();
        String tenantId = amcProductInfo.getTenantId();
        String productId = amcProductInfo.getProductId();
        info.setTenantId(tenantId);
        info.setProductId(productId);
        info.setProductName(amcProductInfo.getProductName());
        info.setDiscountType(amcProductInfo.getCalcType());
        info.setPriority(amcProductInfo.getPriority());
        info.setCreateTime(amcProductInfo.getCreateTime());
        info.setEffectDate(amcProductInfo.getEffectDate());
        info.setExpireDate(amcProductInfo.getExpireDate());
        info.setStatus(amcProductInfo.getStatus());
        info.setRemark(amcProductInfo.getRemark());
        List<ExtendInfo> extendInfoList = this.assemableExtendInfo(tenantId, productId);
        info.setExtendInfoList(extendInfoList);
        return info;
    }

    /**
     * 组装扩展信息
     * @param tenantId
     * @param productId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private List<ExtendInfo> assemableExtendInfo(String tenantId, String productId) {
        List<ExtendInfo> extendInfoList = new ArrayList<ExtendInfo>();
        List<AmcProductExt> extinfos = amcProductExtAtomSV.queryAmcProductExt(tenantId, productId, null);
        if(CollectionUtil.isEmpty(extinfos)) {
            return extendInfoList;
        }
        
        for(AmcProductExt extInfo : extinfos) {
            ExtendInfo extendInfo = new ExtendInfo();
            extendInfo.setAttrName(extInfo.getExtName());
            extendInfo.setAttrValue(extInfo.getExtValue());
            extendInfoList.add(extendInfo);
        }
        return extendInfoList;
    }
    
    /**
     * 获取关联账单科目
     * @param tenantId
     * @param productId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private List<String> getRelatedSubjectList(String tenantId, String productId) {
        AmcProductDetail detail = amcProductDetailAtomSV.getAmcProductDetail(tenantId, productId);
        List<String> relatedSubjectList = new ArrayList<String>();
        if(detail == null) {
            return relatedSubjectList;
        }
        if(!StringUtil.isBlank(detail.getNewSubjectId())) {
            relatedSubjectList.add(detail.getNewSubjectId());
        }
        return relatedSubjectList;
    }

    @Override
    public BillDiscountProductInfo queryBillDiscountProduct(BillDiscountProductQueryRequest request)
            throws BusinessException {
        AmcProductInfo amcProductInfo = amcProductInfoAtomSV.getAmcProductInfo(
                request.getTenantId(), request.getProductId());
        if(amcProductInfo == null) {
            return null;
        }
        BillDiscountProductInfo info = this.assemableBillDiscountProductInfo(amcProductInfo);
        List<String> relatedSubjectList = this.getRelatedSubjectList(request.getTenantId(), request.getProductId());
        info.setRelatedSubjectList(relatedSubjectList);
        return info;
    }
}
