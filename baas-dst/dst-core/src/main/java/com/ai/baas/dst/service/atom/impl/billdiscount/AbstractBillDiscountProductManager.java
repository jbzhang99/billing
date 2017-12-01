package com.ai.baas.dst.service.atom.impl.billdiscount;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.baas.dst.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.baas.dst.util.DstSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠产品管理抽象类
 * @author wangluyang
 *
 */
public abstract class AbstractBillDiscountProductManager {
    
    private static final Logger LOG = LogManager.getLogger(AbstractBillDiscountProductManager.class);
    
    @Autowired
    protected IDiscountInfoAtomSV discountInfoAtomSV;
    
//    @Autowired
//    protected IAmcProductDetailAtomSV amcProductDetailAtomSV;
    
    @Autowired
    protected IDiscountExtAtomSV discountExtAtomSV;
    
    /**
     * 新增账务优惠规则
     * @param vo
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    public String addBillDiscountProduct(BillDiscountVo vo) throws BusinessException {
        /* 1.根据优惠类型进行参数校验  */
        this.validateBillDiscountProductVo(vo);
        /* 2.保存账务优惠产品信息  */
        String discountId = saveBillDiscountProductInfo(vo);
//        /* 3.保存账务优惠产品明细信息  */
//        this.saveBillDiscountProductDetail(discountId, vo);
        /* 4.保存账务优惠产品扩展信息  */
        this.saveBillDiscountProductExtendInfo(discountId, vo);
        return discountId;
    }  

    /**
     * 根据优惠类型进行参数校验(新增)
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void validateBillDiscountProductVo(BillDiscountVo vo) throws BusinessException;
    
//    /**
//     * 保存账务优惠产品明细信息
//     * @ApiDocMethod
//     * @ApiCode
//     */
//    public abstract void saveBillDiscountProductDetail(String discountId, BillDiscountVo vo);
    
    /**
     * 构造账务优惠产品扩展信息
     * 
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract Map<String, String> assembleExtendInfo(String discountId, BillDiscountVo vo);
    
    /**
     * 保存账务优惠产品扩展信息(仅科目)
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void saveExtendSubjectInfo(String discountId, BillDiscountVo vo);
    
    /**
     * 保存账务优惠产品信息
     * @param vo
     * @ApiDocMethod
     * @ApiCode
     */
    private String saveBillDiscountProductInfo(BillDiscountVo vo) {
        String discountId = String.valueOf(DstSeqUtil.getDiscountInfoId());
        LOG.info("开始保存账务优惠产品信息，产品ID: " + discountId);
        DiscountInfo info = new DiscountInfo();
        info.setTenantId(vo.getTenantId());
        info.setDiscountId(discountId);
        info.setDiscountName(vo.getDiscountName());
        info.setPriority(DstConstants.DiscountInfo.DEFAULT_PRIORITY);
        info.setStatus(DstConstants.DiscountInfo.Status.TO_BE_EFFECTIVE);
        info.setCreateTime(DateUtil.getSysDate());
        info.setEffectDate(DateUtil.getTimestamp(vo.getEffectDate(),
                            DateUtil.DATETIME_FORMAT));
        info.setExpireDate(DateUtil.getTimestamp(vo.getExpireDate(),
                DateUtil.DATETIME_FORMAT));
        if(!StringUtil.isBlank(vo.getRemark())) {
            info.setRemark(vo.getRemark());
        }
        info.setCalcType(vo.getDiscountType());
        if(StringUtil.isBlank(vo.getAllPrdDiscount())){
        	info.setAllPrdDiscount(DstConstants.DiscountInfo.PrdDiscountType.DEFAULT);
        }else{
        	info.setAllPrdDiscount(vo.getAllPrdDiscount().trim());
        }
        discountInfoAtomSV.saveDiscountInfo(info);
        LOG.info("保存账务优惠产品信息结束：OK");
        return discountId;
    }
    
    /**
     * 保存账务优惠产品扩展信息
     * @param productId
     * @param vo
     * @ApiDocMethod
     * @ApiCode
     */
    private void saveBillDiscountProductExtendInfo(String discountId, BillDiscountVo vo) {
        LOG.info("开始保存账务优惠产品扩展信息");
    	this.saveExtendSubjectInfo(discountId, vo);
        Map<String, String> extendInfoMap = this.assembleExtendInfo(discountId, vo);
        if(extendInfoMap.isEmpty()) {
            return;
        }
        Set<Entry<String, String>> entrySet = extendInfoMap.entrySet();
        Iterator<Entry<String, String>> iterator = entrySet.iterator();
        DiscountExt record = new DiscountExt();
        record.setTenantId(vo.getTenantId());
        record.setDiscountId(discountId);
        while(iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            record.setExtName(entry.getKey());
            record.setExtValue(entry.getValue());
            discountExtAtomSV.saveDiscountExt(record);
        }
        LOG.info("保存账务优惠产品扩展信息结束：OK");
    }
    
    /**
     * 更新账单优惠活动产品入口
     * @param vo
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    public void updateBillDiscountProduct(BillDiscountUpdateVo vo) throws BusinessException {
        /* 1.根据优惠类型进行参数校验  */
        this.validateBillDiscountProductUpdateVo(vo);
        /* 2.更新账务优惠产品信息  */
        this.updateBillDiscountProductInfo(vo);
//        /* 3.更新账务优惠产品明细信息  */
//        this.updateBillDiscountProductDetail(vo);
        /* 4.更新账务优惠产品扩展信息  */
        this.updateBillDiscountProductExtendInfo(vo);
    }
    
    /**
     * 根据优惠类型进行参数校验(变更类)
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void validateBillDiscountProductUpdateVo(BillDiscountUpdateVo vo)
            throws BusinessException;
    
//    /**
//     * 更新账务优惠产品明细信息
//     * @ApiDocMethod
//     * @ApiCode
//     */
//    public abstract void updateBillDiscountProductDetail(BillDiscountUpdateVo vo);
    
    /**
     * 构造账务优惠产品扩展信息(变更操作)
     * 
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract Map<String, String> assembleExtendInfo(BillDiscountUpdateVo vo);
    
    /**
     * 更新账务优惠产品扩展信息(仅科目)
     * @ApiDocMethod
     * @ApiCode
     */
    public abstract void modifyExtendSubjectInfo(BillDiscountUpdateVo vo);
    
    /**
     * 更新账务优惠产品信息
     * @param vo
     * @ApiDocMethod
     * @ApiCode
     */
    private String updateBillDiscountProductInfo(BillDiscountUpdateVo vo) {
        String discountId = vo.getDiscountId();
        LOG.info("开始变更账务优惠产品信息，产品ID: " + discountId);
        DiscountInfo info = new DiscountInfo();
        info.setTenantId(vo.getTenantId());
        info.setDiscountId(discountId);
        if(!StringUtil.isBlank(vo.getDiscountName())) {
            info.setDiscountName(vo.getDiscountName());
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
        if(StringUtil.isBlank(vo.getAllPrdDiscount())){
        	info.setAllPrdDiscount(DstConstants.DiscountInfo.PrdDiscountType.DEFAULT);
        }else{
        	info.setAllPrdDiscount(vo.getAllPrdDiscount().trim());
        }
        discountInfoAtomSV.updateDiscountInfo(info);
        LOG.info("更新账务优惠产品信息成功");
        return discountId;
    }
    
    /**
     * 更新账务优惠产品扩展信息
     * @param productId
     * @param vo
     * @ApiDocMethod
     * @ApiCode
     */
    private void updateBillDiscountProductExtendInfo(BillDiscountUpdateVo vo) {
        LOG.info("开始更新账务优惠产品扩展信息");
        this.modifyExtendSubjectInfo(vo);
        Map<String, String> extendInfoMap = this.assembleExtendInfo(vo);
        if(extendInfoMap.isEmpty()) {
            return;
        }
        Set<Entry<String, String>> entrySet = extendInfoMap.entrySet();
        Iterator<Entry<String, String>> iterator = entrySet.iterator();
        DiscountExt record = new DiscountExt();
        record.setTenantId(vo.getTenantId());
        record.setDiscountId(vo.getDiscountId());
        while(iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            record.setExtName(entry.getKey());
            record.setExtValue(entry.getValue());
            discountExtAtomSV.updateDiscountExt(record);
        }
        LOG.info("更新账务优惠产品扩展信息成功");
    }
    
}
