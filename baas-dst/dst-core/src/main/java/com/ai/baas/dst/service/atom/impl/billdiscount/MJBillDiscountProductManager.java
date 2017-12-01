package com.ai.baas.dst.service.atom.impl.billdiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * 满减优惠具体实现类
 * @author wangluyang
 *
 */
@Component(value = "MJBillDiscountProductManager")
public class MJBillDiscountProductManager extends AbstractBillDiscountProductManager {

    private static final Logger LOG = LogManager.getLogger(MJBillDiscountProductManager.class);
    
    @Override
    public void validateBillDiscountProductVo(BillDiscountVo vo) throws BusinessException {
//        if (CollectionUtil.isEmpty(vo.getRelatedSubjectList())) {
//            throw new BusinessException("关联账单科目不能为空");
//        }       
    }

    @Override
    public Map<String, String> assembleExtendInfo(String productId, BillDiscountVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_AMOUNT, vo.getFullCostAmount());
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_UNIT_ID, vo.getFullCostUnitId());
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.DISCOUNT_RULL_TYPE, vo.getDiscountRullType());
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.DISCOUNT_AMOUNT, vo.getDiscountAmount());
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.DISCOUNT_UNIT_ID, vo.getDiscountUnitId());
        return extendInfoMap;
    }

    @Override
    public void saveExtendSubjectInfo(String discountId, BillDiscountVo vo) {
    	DiscountExt record = new DiscountExt();
        record.setTenantId(vo.getTenantId());
        record.setDiscountId(discountId);
        if(StringUtils.equals(DstConstants.DiscountInfo.PrdDiscountType.DEFAULT, vo.getAllPrdDiscount())){
	        List<String> discountProductList = vo.getDiscountProductList();
	        for(String productId : discountProductList) {
	            record.setExtName(DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
	            record.setExtValue(productId);
	            discountExtAtomSV.saveDiscountExt(record);
	        }
        }
    }

    @Override
    public void validateBillDiscountProductUpdateVo(BillDiscountUpdateVo vo)
            throws BusinessException {
//        if(vo.getFullCostAmount() != null && vo.getFullCostAmount().doubleValue() <= 0) {
//            throw new BusinessException("满减到达金额必须大于零");
//        }
//        if(vo.getDiscountAmount() != null && vo.getDiscountAmount() <= 0) {
//            throw new BusinessException("扣减金额必须大于零");
//        } 
    }

    @Override
    public Map<String, String> assembleExtendInfo(BillDiscountUpdateVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        if(vo.getFullCostAmount() != null) {
            extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        }
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.FULL_COST_UNIT_ID, vo.getFullCostUnitId());
        if(vo.getDiscountAmount() != null) {
            extendInfoMap.put(DstConstants.DiscountInfo.AttrName.DISCOUNT_AMOUNT, String.valueOf(vo.getDiscountAmount()));
        }
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.DISCOUNT_RULL_TYPE, vo.getDiscountRullType());
        extendInfoMap.put(DstConstants.DiscountInfo.AttrName.DISCOUNT_UNIT_ID, vo.getDiscountUnitId());
        return extendInfoMap;
    }

    @Override
    public void modifyExtendSubjectInfo(BillDiscountUpdateVo vo) {
        String tenantId = vo.getTenantId();
        String discountId = vo.getDiscountId();
        discountExtAtomSV.deleteDiscountExt(tenantId, discountId,
        		DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
        List<String> billSubjectList = vo.getDiscountProductList();
        if (CollectionUtil.isEmpty(billSubjectList)) {
            return;
        }
        DiscountExt record = new DiscountExt();
        record.setTenantId(tenantId);
        record.setDiscountId(discountId);
        for (String subjectId : billSubjectList) {
            record.setExtName(DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
            record.setExtValue(subjectId);
            discountExtAtomSV.saveDiscountExt(record);
        }
    }

}
