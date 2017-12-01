package com.ai.baas.amc.service.atom.impl.billdiscount;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfo;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductDetailAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductExtAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductInfoAtomSV;
import com.ai.baas.amc.util.AmcSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠产品管理抽象类
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public abstract class AbstractBillDiscountProductManager {
    
    private static final Logger LOG = LogManager.getLogger(AbstractBillDiscountProductManager.class);
    
    @Autowired
    protected IAmcProductInfoAtomSV amcProductInfoAtomSV;
    
    @Autowired
    protected IAmcProductDetailAtomSV amcProductDetailAtomSV;
    
    @Autowired
    protected IAmcProductExtAtomSV amcProductExtAtomSV;
    
    /**
     * 新增账务优惠规则
     * @param vo
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public String addBillDiscountProduct(BillDiscountProductVo vo) throws BusinessException {
        /* 1.根据优惠类型进行参数校验  */
        this.validateBillDiscountProductVo(vo);
        /* 2.保存账务优惠产品信息  */
        String productId = saveBillDiscountProductInfo(vo);
        /* 3.保存账务优惠产品明细信息  */
        this.saveBillDiscountProductDetail(productId, vo);
        /* 4.保存账务优惠产品扩展信息  */
        this.saveBillDiscountProductExtendInfo(productId, vo);
        return productId;
    }  

    /**
     * 根据优惠类型进行参数校验(新增)
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void validateBillDiscountProductVo(BillDiscountProductVo vo) throws BusinessException;
    
    /**
     * 保存账务优惠产品明细信息
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void saveBillDiscountProductDetail(String productId, BillDiscountProductVo vo);
    
    /**
     * 构造账务优惠产品扩展信息
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract Map<String, String> assembleExtendInfo(String productId, BillDiscountProductVo vo);
    
    /**
     * 保存账务优惠产品扩展信息(仅科目)
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void saveExtendSubjectInfo(String productId, BillDiscountProductVo vo);
    
    /**
     * 保存账务优惠产品信息
     * @param vo
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private String saveBillDiscountProductInfo(BillDiscountProductVo vo) {
        String productId = String.valueOf(AmcSeqUtil.createProductId());
        LOG.info("开始保存账务优惠产品信息，产品ID: " + productId);
        AmcProductInfo info = new AmcProductInfo();
        info.setTenantId(vo.getTenantId());
        info.setProductId(productId);
        info.setProductName(vo.getProductName());
        info.setPriority(AmcConstants.AmcProductInfo.DEFAULT_PRIORITY);
        info.setStatus(AmcConstants.AmcProductInfo.Status.TO_BE_EFFECTIVE);
        info.setCreateTime(DateUtil.getSysDate());
        info.setEffectDate(DateUtil.getTimestamp(vo.getEffectDate(),
                            DateUtil.DATETIME_FORMAT));
        info.setExpireDate(DateUtil.getTimestamp(vo.getExpireDate(),
                DateUtil.DATETIME_FORMAT));
        if(!StringUtil.isBlank(vo.getRemark())) {
            info.setRemark(vo.getRemark());
        }
        info.setCalcType(vo.getDiscountType());
        amcProductInfoAtomSV.saveAmcProductInfo(info);
        LOG.info("保存账务优惠产品信息结束：OK");
        return productId;
    }
    
    /**
     * 保存账务优惠产品扩展信息
     * @param productId
     * @param vo
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private void saveBillDiscountProductExtendInfo(String productId, BillDiscountProductVo vo) {
        LOG.info("开始保存账务优惠产品扩展信息");
        this.saveExtendSubjectInfo(productId, vo);
        Map<String, String> extendInfoMap = this.assembleExtendInfo(productId, vo);
        if(extendInfoMap.isEmpty()) {
            return;
        }
        Set<Entry<String, String>> entrySet = extendInfoMap.entrySet();
        Iterator<Entry<String, String>> iterator = entrySet.iterator();
        AmcProductExt record = new AmcProductExt();
        record.setTenantId(vo.getTenantId());
        record.setProductId(productId);
        while(iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            record.setExtName(entry.getKey());
            record.setExtValue(entry.getValue());
            amcProductExtAtomSV.saveAmcProductExt(record);
        }
        LOG.info("保存账务优惠产品扩展信息结束：OK");
    }
    
    /**
     * 更新账单优惠活动产品入口
     * @param vo
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public void updateBillDiscountProduct(BillDiscountProductUpdateVo vo) throws BusinessException {
        /* 1.根据优惠类型进行参数校验  */
        this.validateBillDiscountProductUpdateVo(vo);
        /* 2.更新账务优惠产品信息  */
        this.updateBillDiscountProductInfo(vo);
        /* 3.更新账务优惠产品明细信息  */
        this.updateBillDiscountProductDetail(vo);
        /* 4.更新账务优惠产品扩展信息  */
        this.updateBillDiscountProductExtendInfo(vo);
    }
    
    /**
     * 根据优惠类型进行参数校验(变更类)
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void validateBillDiscountProductUpdateVo(BillDiscountProductUpdateVo vo)
            throws BusinessException;
    
    /**
     * 更新账务优惠产品明细信息
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void updateBillDiscountProductDetail(BillDiscountProductUpdateVo vo);
    
    /**
     * 构造账务优惠产品扩展信息(变更操作)
     * 
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract Map<String, String> assembleExtendInfo(BillDiscountProductUpdateVo vo);
    
    /**
     * 更新账务优惠产品扩展信息(仅科目)
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void modifyExtendSubjectInfo(BillDiscountProductUpdateVo vo);
    
    /**
     * 更新账务优惠产品信息
     * @param vo
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private String updateBillDiscountProductInfo(BillDiscountProductUpdateVo vo) {
        String productId = vo.getProductId();
        LOG.info("开始变更账务优惠产品信息，产品ID: " + productId);
        AmcProductInfo info = new AmcProductInfo();
        info.setTenantId(vo.getTenantId());
        info.setProductId(productId);
        if(!StringUtil.isBlank(vo.getProductName())) {
            info.setProductName(vo.getProductName());
        }
        if(!StringUtil.isBlank(vo.getStatus())) {
            info.setStatus(vo.getStatus());
        }
        if(!StringUtil.isBlank(vo.getEffectDate())) {
            info.setEffectDate(DateUtil.getTimestamp(vo.getEffectDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        if(!StringUtil.isBlank(vo.getExpireDate())) {
            info.setExpireDate(DateUtil.getTimestamp(vo.getExpireDate(),
                    DateUtil.DATETIME_FORMAT));  
        }
        if(!StringUtil.isBlank(vo.getRemark())) {
            info.setRemark(vo.getRemark());
        }
        amcProductInfoAtomSV.updateAmcProductInfo(info);
        LOG.info("更新账务优惠产品信息成功");
        return productId;
    }
    
    /**
     * 更新账务优惠产品扩展信息
     * @param productId
     * @param vo
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private void updateBillDiscountProductExtendInfo(BillDiscountProductUpdateVo vo) {
        LOG.info("开始更新账务优惠产品扩展信息");
        this.modifyExtendSubjectInfo(vo);
        Map<String, String> extendInfoMap = this.assembleExtendInfo(vo);
        if(extendInfoMap.isEmpty()) {
            return;
        }
        Set<Entry<String, String>> entrySet = extendInfoMap.entrySet();
        Iterator<Entry<String, String>> iterator = entrySet.iterator();
        AmcProductExt record = new AmcProductExt();
        record.setTenantId(vo.getTenantId());
        record.setProductId(vo.getProductId());
        while(iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            record.setExtName(entry.getKey());
            record.setExtValue(entry.getValue());
            amcProductExtAtomSV.updateAmcProductExt(record);
        }
        LOG.info("更新账务优惠产品扩展信息成功");
    }
    
}
