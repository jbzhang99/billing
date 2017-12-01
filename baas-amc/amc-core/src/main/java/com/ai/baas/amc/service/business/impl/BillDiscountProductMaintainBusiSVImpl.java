package com.ai.baas.amc.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductDeleteRequest;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfo;
import com.ai.baas.amc.service.atom.impl.billdiscount.AbstractBillDiscountProductManager;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductDetailAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductExtAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductInfoAtomSV;
import com.ai.baas.amc.service.business.interfaces.IBillDiscountProductMaintainBusiSV;
import com.ai.baas.amc.util.ServiceFactoryUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠服务实现类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class BillDiscountProductMaintainBusiSVImpl implements IBillDiscountProductMaintainBusiSV {
    
    @Autowired
    protected ApplicationContext ctx;
    
    @Autowired
    protected IAmcProductInfoAtomSV amcProductInfoAtomSV;
    
    @Autowired
    protected IAmcProductDetailAtomSV amcProductDetailAtomSV;
    
    @Autowired
    protected IAmcProductExtAtomSV amcProductExtAtomSV; 

    @Override
    public String addBillDiscountProduct(BillDiscountProductVo vo) throws BusinessException {
        String serviceName = ServiceFactoryUtil.getBillDiscountProductManager(vo.getDiscountType());
        AbstractBillDiscountProductManager manager = (AbstractBillDiscountProductManager) ctx
                .getBean(serviceName);
        return manager.addBillDiscountProduct(vo);
    }

    @Override
    public void deleteBillDiscountProduct(BillDiscountProductDeleteRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        String productId = request.getProductId();
        amcProductInfoAtomSV.deleteAmcProductInfo(tenantId, productId);
        amcProductDetailAtomSV.deleteAmcProductDetail(tenantId, productId);
        amcProductExtAtomSV.deleteAmcProductExt(tenantId, productId, null);
    }

    @Override
    public void updateBillDiscountProduct(BillDiscountProductUpdateVo vo) throws BusinessException {
        AmcProductInfo info = amcProductInfoAtomSV.getAmcProductInfo(vo.getTenantId(), vo.getProductId());
        if(info == null) {
            return;
        }
        if(StringUtil.isBlank(info.getCalcType())) {
            throw new SystemException("账单优惠活动变更失败，获取不到此账单优惠活动的优惠类型！");
        }
        String serviceName = ServiceFactoryUtil.getBillDiscountProductManager(info.getCalcType());
        AbstractBillDiscountProductManager manager = (AbstractBillDiscountProductManager) ctx
                .getBean(serviceName);
        manager.updateBillDiscountProduct(vo);
    }

}
