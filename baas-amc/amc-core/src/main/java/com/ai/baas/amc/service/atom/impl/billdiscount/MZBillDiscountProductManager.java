package com.ai.baas.amc.service.atom.impl.billdiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.baas.amc.api.billdiscount.param.GiftProduct;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcProductDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * 满赠优惠具体实现类
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component(value = "MZBillDiscountProductManager")
public class MZBillDiscountProductManager extends AbstractBillDiscountProductManager {
    
    private static final Logger LOG = LogManager.getLogger(MZBillDiscountProductManager.class);

    @Override
    public void validateBillDiscountProductVo(BillDiscountProductVo vo) throws BusinessException {
        if(vo.getFullCostAmount() <= 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "满赠到达金额必须大于零");
        }
        if(vo.getGiftProduct() == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "满赠活动的赠品不能为空");
        }
        GiftProduct giftProduct = vo.getGiftProduct();
        if(CollectionUtil.isEmpty(giftProduct.getProductIdList())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "赠送业务列表不能为空");
        }
        if (StringUtil.isBlank(giftProduct.getActiveMode())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "生效方式不能为空");
        }
        this.checkActiveMode(giftProduct.getActiveMode());
        if (StringUtil.isBlank(giftProduct.getActivePeriod())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "赠送业务周期不能为空");
        }
        this.checkActivePeriod(giftProduct.getActivePeriod());
        if(AmcConstants.AmcProductInfo.ActiveMode.FIXED_DAY.equals(giftProduct.getActiveMode()) && StringUtil.isBlank(giftProduct.getEffectDate())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "赠送业务生效方式选择指定日期赠送时生效时间不能为空");
        }
        if (!StringUtil.isBlank(giftProduct.getEffectDate()) && !DateUtil.isValidDate(giftProduct.getEffectDate(), DateUtil.DATETIME_FORMAT)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_TYPE_NOT_RIGHT, "赠送产品指定生效日期格式不正确["
                    + JSON.toJSONString(giftProduct.getEffectDate()) + "]");
        }
    }

    @Override
    public void saveBillDiscountProductDetail(String productId, BillDiscountProductVo vo) {
        LOG.info("开始保存账务优惠产品明细信息");
        AmcProductDetail detailInfo = new AmcProductDetail();
        detailInfo.setTenantId(vo.getTenantId());
        detailInfo.setProductId(productId);
        detailInfo.setPriority(AmcConstants.AmcProductInfo.DEFAULT_PRIORITY);
        detailInfo.setBillSubjectId(AmcConstants.AmcProductInfo.BILL_SUBJECT_CODE);
        detailInfo.setRefSubjectId(AmcConstants.AmcProductInfo.REFER_SUBJECT_CODE);
        detailInfo.setCalcType(AmcConstants.AmcProductInfo.CalcType.CALC_TYPE_MZ);
        amcProductDetailAtomSV.saveAmcProductDetail(detailInfo);
        LOG.info("保存账务优惠产品明细信息结束：OK");
    }

    @Override
    public Map<String, String> assembleExtendInfo(String productId, BillDiscountProductVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        GiftProduct giftProduct = vo.getGiftProduct();
        extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.ACTIVE_MODE, giftProduct.getActiveMode());
        extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.ACTIVE_PERIOD, giftProduct.getActivePeriod());
        if(AmcConstants.AmcProductInfo.ActiveMode.FIXED_DAY.equals(giftProduct.getActiveMode())) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.EFFECT_DATE, giftProduct.getEffectDate());
        }
        return extendInfoMap;
    }

    @Override
    public void saveExtendSubjectInfo(String productId, BillDiscountProductVo vo) {
        AmcProductExt record = new AmcProductExt();
        record.setTenantId(vo.getTenantId());
        record.setProductId(productId);
        List<String> billSubjectList = vo.getBillSubjectList();
        for(String subjectId : billSubjectList) {
            record.setExtName(AmcConstants.AmcProductInfo.AttrName.REFER_SUBJECT_CODE);
            record.setExtValue(subjectId);
            amcProductExtAtomSV.saveAmcProductExt(record);
        }
        List<String> productIdList = vo.getGiftProduct().getProductIdList();
        for(String productIdStr : productIdList) {
            record.setExtName(AmcConstants.AmcProductInfo.AttrName.BILL_SUBJECT_CODE);
            record.setExtValue(productIdStr);
            amcProductExtAtomSV.saveAmcProductExt(record);
        }
    }

    @Override
    public void validateBillDiscountProductUpdateVo(BillDiscountProductUpdateVo vo)
            throws BusinessException {
        if(vo.getFullCostAmount() != null && vo.getFullCostAmount().doubleValue() <= 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "满赠到达金额必须大于零");
        }
        GiftProduct giftProduct = vo.getGiftProduct();
        if(giftProduct == null) {
            return;
        }
        if(!StringUtil.isBlank(giftProduct.getActiveMode())) {
            this.checkActiveMode(giftProduct.getActiveMode());
        }
        if(!StringUtil.isBlank(giftProduct.getActivePeriod())) {
            this.checkActivePeriod(giftProduct.getActivePeriod());
        }
        if(AmcConstants.AmcProductInfo.ActiveMode.FIXED_DAY.equals(giftProduct.getActiveMode()) && StringUtil.isBlank(giftProduct.getEffectDate())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "赠送业务生效方式选择指定日期赠送时生效时间不能为空");
        }
        if (!StringUtil.isBlank(giftProduct.getEffectDate()) && !DateUtil.isValidDate(giftProduct.getEffectDate(), DateUtil.DATETIME_FORMAT)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_TYPE_NOT_RIGHT, "赠送产品指定生效日期格式不正确["
                    + JSON.toJSONString(giftProduct.getEffectDate()) + "]");
        }  
    }

    @Override
    public void updateBillDiscountProductDetail(BillDiscountProductUpdateVo vo) {
        
    }

    @Override
    public Map<String, String> assembleExtendInfo(BillDiscountProductUpdateVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        if(vo.getFullCostAmount() != null) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        }
        GiftProduct giftProduct = vo.getGiftProduct();
        if(giftProduct == null) {
            return extendInfoMap;
        }
        if (!StringUtil.isBlank(giftProduct.getActiveMode())) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.ACTIVE_MODE, giftProduct.getActiveMode());
        }
        if (!StringUtil.isBlank(giftProduct.getActivePeriod())) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.ACTIVE_PERIOD, giftProduct.getActivePeriod());
        }
        if(AmcConstants.AmcProductInfo.ActiveMode.FIXED_DAY.equals(giftProduct.getActiveMode()) && !StringUtil.isBlank(giftProduct.getEffectDate())) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.EFFECT_DATE, giftProduct.getEffectDate());
        }
        return extendInfoMap;
    }

    @Override
    public void modifyExtendSubjectInfo(BillDiscountProductUpdateVo vo) {
        String tenantId = vo.getTenantId();
        String productId = vo.getProductId();
        List<String> billSubjectList = vo.getBillSubjectList();
        AmcProductExt record = new AmcProductExt();
        record.setTenantId(tenantId);
        record.setProductId(productId);
        if (!CollectionUtil.isEmpty(billSubjectList)) {
            amcProductExtAtomSV.deleteAmcProductExt(tenantId, productId,
                    AmcConstants.AmcProductInfo.AttrName.REFER_SUBJECT_CODE);

            for (String subjectId : billSubjectList) {
                record.setExtName(AmcConstants.AmcProductInfo.AttrName.REFER_SUBJECT_CODE);
                record.setExtValue(subjectId);
                amcProductExtAtomSV.saveAmcProductExt(record);
            }
        }

        GiftProduct giftProduct = vo.getGiftProduct();
        if(giftProduct == null) {
            return;
        }
        List<String> productIdList = giftProduct.getProductIdList();
        if(CollectionUtil.isEmpty(productIdList)) {
            return;
        }
        amcProductExtAtomSV.deleteAmcProductExt(tenantId, productId,
                AmcConstants.AmcProductInfo.AttrName.BILL_SUBJECT_CODE);
        for(String productIdStr : productIdList) {
            record.setExtName(AmcConstants.AmcProductInfo.AttrName.BILL_SUBJECT_CODE);
            record.setExtValue(productIdStr);
            amcProductExtAtomSV.saveAmcProductExt(record);
        }    
    }
    
    /**
     * 校验生效方式取值
     * @param activeMode
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private void checkActiveMode(String activeMode) {
        if (!AmcConstants.AmcProductInfo.ActiveMode.IMMEDIATELY.equals(activeMode)
                && !AmcConstants.AmcProductInfo.ActiveMode.NEXT_MONTH.equals(activeMode)
                && !AmcConstants.AmcProductInfo.ActiveMode.FIXED_DAY.equals(activeMode)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_WRONG, "生效方式不合法");
        }
    }
    
    /**
     * 校验赠送业务周期取值
     * @param activePeriod
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private void checkActivePeriod(String activePeriod) {
        if (!AmcConstants.AmcProductInfo.ActivePeriod.ONE_MONTH.equals(activePeriod)
                && !AmcConstants.AmcProductInfo.ActivePeriod.THREE_MONTHS.equals(activePeriod)
                && !AmcConstants.AmcProductInfo.ActivePeriod.FIXED_DAY.equals(activePeriod)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_WRONG, "赠送业务周期不合法");
        }
    }

}
