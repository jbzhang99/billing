package com.ai.baas.dst.service.atom.impl.billdiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.baas.dst.api.billsdiscount.params.GiftProduct;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetail;
import com.ai.baas.dst.service.atom.interfaces.IGiftInfoDetailAtomSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * 满赠优惠具体实现类
 * @author wangluyang
 *
 */
@Component(value = "MZBillDiscountProductManager")
public class MZBillDiscountProductManager extends AbstractBillDiscountProductManager {
    
    private static final Logger LOG = LogManager.getLogger(MZBillDiscountProductManager.class);

    @Autowired
    protected IGiftInfoDetailAtomSV giftInfoDetailAtomSV;
    
    @Override
    public void validateBillDiscountProductVo(BillDiscountVo vo) throws BusinessException {
//        if(vo.getGiftProduct() == null) {
//            throw new BusinessException("满赠活动的赠品不能为空");
//        }
//        GiftProduct giftProduct = vo.getGiftProduct();
//        if(CollectionUtil.isEmpty(giftProduct.getProductIdList())) {
//            throw new BusinessException("赠送业务列表不能为空");
//        }
//        if (StringUtil.isBlank(giftProduct.getActiveMode())) {
//            throw new BusinessException("生效方式不能为空");
//        }
//        this.checkActiveMode(giftProduct.getActiveMode());
//        if (StringUtil.isBlank(giftProduct.getActivePeriod())) {
//            throw new BusinessException("赠送业务周期不能为空");
//        }
//        this.checkActivePeriod(giftProduct.getActivePeriod());
//        if(DstConstants.DiscountInfo.ActiveMode.FIXED_DAY.equals(giftProduct.getActiveMode()) && StringUtil.isBlank(giftProduct.getEffectDate())) {
//            throw new BusinessException("赠送业务生效方式选择指定日期赠送时生效时间不能为空");
//        }
//        if (!StringUtil.isBlank(giftProduct.getEffectDate()) && !DateUtil.isValidDate(giftProduct.getEffectDate(), DateUtil.DATETIME_FORMAT)) {
//            throw new BusinessException("赠送产品指定生效日期格式不正确["
//                    + JSON.toJSONString(giftProduct.getEffectDate()) + "]");
//        }
    }

    @Override
    public Map<String, String> assembleExtendInfo(String productId, BillDiscountVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_UNIT_ID, vo.getFullCostUnitId());
        GiftInfoDetail giftInfoDetail = new GiftInfoDetail();
        giftInfoDetail.setTenantId(vo.getTenantId());
        giftInfoDetail.setBusinessPeriod(vo.getGiftProduct().getActivePeriod());
        giftInfoDetail.setActiveMode(vo.getGiftProduct().getActiveMode());
        if(DstConstants.DiscountInfo.ActiveMode.FIXED_DAY.equals(vo.getGiftProduct().getActiveMode())) {
        	giftInfoDetail.setEffectDate(DateUtil.getTimestamp(vo.getGiftProduct().getEffectDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        giftInfoDetail.setCreateTime(DateUtil.getSysDate());
        giftInfoDetail.setDiscountId(productId);
        giftInfoDetail.setCashAmount(String.valueOf(vo.getGiftProduct().getCashAmount()));
        giftInfoDetail.setStatus(DstConstants.DiscountInfo.Status.TO_BE_EFFECTIVE);
        giftInfoDetail.setGiftType(vo.getGiftProduct().getGiftType());
        giftInfoDetail.setVirtualCoinsNum(String.valueOf(vo.getGiftProduct().getVirtualCoinsNum()));
        giftInfoDetail.setOperatorId(vo.getTenantId());
        giftInfoDetailAtomSV.saveGiftInfoDetail(giftInfoDetail);
        return extendInfoMap;
    }

    @Override
    public void saveExtendSubjectInfo(String discountId, BillDiscountVo vo) {
    	DiscountExt record = new DiscountExt();
    	record.setTenantId(vo.getTenantId());
        record.setDiscountId(discountId);
    	if(StringUtils.equals(DstConstants.DiscountInfo.PrdDiscountType.DEFAULT, vo.getAllPrdDiscount())){
            List<String> billSubjectList = vo.getDiscountProductList();
            for(String subjectId : billSubjectList) {
                record.setExtName(DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
                record.setExtValue(subjectId);
                discountExtAtomSV.saveDiscountExt(record);
            }
        }
        if(!CollectionUtil.isEmpty(vo.getGiftProduct().getProductIdList())){
        	 List<String> productIdList = vo.getGiftProduct().getProductIdList();
             for(String productIdStr : productIdList) {
                 record.setExtName(DstConstants.DiscountInfo.AttrName.GIFT_PRODUCT_ID);
                 record.setExtValue(productIdStr);
                 discountExtAtomSV.saveDiscountExt(record);
             }
        }
    }

    @Override
    public void validateBillDiscountProductUpdateVo(BillDiscountUpdateVo vo)
            throws BusinessException {
//        if(vo.getFullCostAmount() != null && vo.getFullCostAmount().doubleValue() <= 0) {
//            throw new BusinessException("满赠到达金额必须大于零");
//        }
//        GiftProduct giftProduct = vo.getGiftProduct();
//        if(giftProduct == null) {
//            return;
//        }
//        if(!StringUtil.isBlank(giftProduct.getActiveMode())) {
//            this.checkActiveMode(giftProduct.getActiveMode());
//        }
//        if(!StringUtil.isBlank(giftProduct.getActivePeriod())) {
//            this.checkActivePeriod(giftProduct.getActivePeriod());
//        }
//        if(DstConstants.DiscountInfo.ActiveMode.FIXED_DAY.equals(giftProduct.getActiveMode()) && StringUtil.isBlank(giftProduct.getEffectDate())) {
//            throw new BusinessException("赠送业务生效方式选择指定日期赠送时生效时间不能为空");
//        }
//        if (!StringUtil.isBlank(giftProduct.getEffectDate()) && !DateUtil.isValidDate(giftProduct.getEffectDate(), DateUtil.DATETIME_FORMAT)) {
//            throw new BusinessException("赠送产品指定生效日期格式不正确["
//                    + JSON.toJSONString(giftProduct.getEffectDate()) + "]");
//        }  
    }

    @Override
    public Map<String, String> assembleExtendInfo(BillDiscountUpdateVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        if(vo.getFullCostAmount() != null) {
            extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        }
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_UNIT_ID, vo.getFullCostUnitId());
        GiftProduct giftProduct = vo.getGiftProduct();
        if(giftProduct == null) {
            return extendInfoMap;
        }
        GiftInfoDetail giftInfoDetail = new GiftInfoDetail();
        giftInfoDetail.setTenantId(vo.getTenantId());
        giftInfoDetail.setBusinessPeriod(vo.getGiftProduct().getActivePeriod());
        giftInfoDetail.setActiveMode(vo.getGiftProduct().getActiveMode());
        if(DstConstants.DiscountInfo.ActiveMode.FIXED_DAY.equals(vo.getGiftProduct().getActiveMode())) {
        	giftInfoDetail.setEffectDate(DateUtil.getTimestamp(vo.getGiftProduct().getEffectDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        giftInfoDetail.setCreateTime(DateUtil.getSysDate());
        giftInfoDetail.setDiscountId(vo.getDiscountId());
        giftInfoDetail.setCashAmount(String.valueOf(vo.getGiftProduct().getCashAmount()));
        giftInfoDetail.setStatus(DstConstants.DiscountInfo.Status.TO_BE_EFFECTIVE);
        giftInfoDetail.setGiftType(vo.getGiftProduct().getGiftType());
        giftInfoDetail.setVirtualCoinsNum(String.valueOf(vo.getGiftProduct().getVirtualCoinsNum()));
        giftInfoDetail.setOperatorId(vo.getTenantId());
        giftInfoDetailAtomSV.updateGiftInfoDetail(giftInfoDetail);
        return extendInfoMap;
    }

    @Override
    public void modifyExtendSubjectInfo(BillDiscountUpdateVo vo) {
        String tenantId = vo.getTenantId();
        String discountId = vo.getDiscountId();
        List<String> billSubjectList = vo.getDiscountProductList();
        DiscountExt record = new DiscountExt();
        record.setTenantId(tenantId);
        record.setDiscountId(discountId);
        discountExtAtomSV.deleteDiscountExt(tenantId, discountId,
        		DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
        if (!CollectionUtil.isEmpty(billSubjectList)) {
            for (String subjectId : billSubjectList) {
                record.setExtName(DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
                record.setExtValue(subjectId);
                discountExtAtomSV.saveDiscountExt(record);
            }
        }

        GiftProduct giftProduct = vo.getGiftProduct();
        if(giftProduct == null) {
            return;
        }
        discountExtAtomSV.deleteDiscountExt(tenantId, discountId,
        		DstConstants.DiscountInfo.AttrName.GIFT_PRODUCT_ID);
        List<String> productIdList = giftProduct.getProductIdList();
        if(CollectionUtil.isEmpty(productIdList)) {
            return;
        }
        for(String productIdStr : productIdList) {
            record.setExtName(DstConstants.DiscountInfo.AttrName.GIFT_PRODUCT_ID);
            record.setExtValue(productIdStr);
            discountExtAtomSV.saveDiscountExt(record);
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
        if (!DstConstants.DiscountInfo.ActiveMode.IMMEDIATELY.equals(activeMode)
                && !DstConstants.DiscountInfo.ActiveMode.NEXT_MONTH.equals(activeMode)
                && !DstConstants.DiscountInfo.ActiveMode.FIXED_DAY.equals(activeMode)) {
            throw new BusinessException("生效方式不合法");
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
        if (!DstConstants.DiscountInfo.ActivePeriod.ONE_MONTH.equals(activePeriod)
                && !DstConstants.DiscountInfo.ActivePeriod.THREE_MONTHS.equals(activePeriod)
                && !DstConstants.DiscountInfo.ActivePeriod.FIXED_DAY.equals(activePeriod)) {
            throw new BusinessException("赠送业务周期不合法");
        }
    }

}
