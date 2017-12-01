package com.ai.baas.amc.service.atom.impl.billdiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcProductDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * 满减优惠具体实现类
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component(value = "MJBillDiscountProductManager")
public class MJBillDiscountProductManager extends AbstractBillDiscountProductManager {

    private static final Logger LOG = LogManager.getLogger(MJBillDiscountProductManager.class);
    
    @Override
    public void validateBillDiscountProductVo(BillDiscountProductVo vo) throws BusinessException {
        if(vo.getFullCostAmount() <= 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "满减到达金额必须大于零");
        }
        if(vo.getDiscountAmount() <= 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "扣减金额必须大于零");
        } 
        if (CollectionUtil.isEmpty(vo.getRelatedSubjectList())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "关联账单科目不能为空");
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
        detailInfo.setNewSubjectId(vo.getRelatedSubjectList().get(0));
        detailInfo.setCalcType(AmcConstants.AmcProductInfo.CalcType.CALC_TYPE_MJ);
        amcProductDetailAtomSV.saveAmcProductDetail(detailInfo);
        LOG.info("保存账务优惠产品明细信息结束：OK");
    }

    @Override
    public Map<String, String> assembleExtendInfo(String productId, BillDiscountProductVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.DISCOUNT_AMOUNT, String.valueOf(vo.getDiscountAmount()));
        return extendInfoMap;
    }

    @Override
    public void saveExtendSubjectInfo(String productId, BillDiscountProductVo vo) {
        AmcProductExt record = new AmcProductExt();
        record.setTenantId(vo.getTenantId());
        record.setProductId(productId);
        List<String> billSubjectList = vo.getBillSubjectList();
        for(String subjectId : billSubjectList) {
            record.setExtName(AmcConstants.AmcProductInfo.AttrName.BILL_SUBJECT_CODE);
            record.setExtValue(subjectId);
            amcProductExtAtomSV.saveAmcProductExt(record);
        }
    }

    @Override
    public void validateBillDiscountProductUpdateVo(BillDiscountProductUpdateVo vo)
            throws BusinessException {
        if(vo.getFullCostAmount() != null && vo.getFullCostAmount().doubleValue() <= 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "满减到达金额必须大于零");
        }
        if(vo.getDiscountAmount() != null && vo.getDiscountAmount() <= 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "扣减金额必须大于零");
        } 
    }

    @Override
    public void updateBillDiscountProductDetail(BillDiscountProductUpdateVo vo) {
        List<String> relatedSubjectList = vo.getRelatedSubjectList();
        if(CollectionUtil.isEmpty(relatedSubjectList)) {
            return;
        }
        LOG.info("开始更新账务优惠产品明细信息");
        AmcProductDetail detailInfo = new AmcProductDetail();
        detailInfo.setTenantId(vo.getTenantId());
        detailInfo.setProductId(vo.getProductId());
        detailInfo.setNewSubjectId(relatedSubjectList.get(0));
        amcProductDetailAtomSV.updateAmcProductDetail(detailInfo);
        LOG.info("更新账务优惠产品明细信息成功");
    }

    @Override
    public Map<String, String> assembleExtendInfo(BillDiscountProductUpdateVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        if(vo.getFullCostAmount() != null) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.FULL_COST_AMOUNT, String.valueOf(vo.getFullCostAmount()));
        }
        if(vo.getDiscountAmount() != null) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.DISCOUNT_AMOUNT, String.valueOf(vo.getDiscountAmount()));
        }
        return extendInfoMap;
    }

    @Override
    public void modifyExtendSubjectInfo(BillDiscountProductUpdateVo vo) {
        List<String> billSubjectList = vo.getBillSubjectList();
        if (CollectionUtil.isEmpty(billSubjectList)) {
            return;
        }
        String tenantId = vo.getTenantId();
        String productId = vo.getProductId();
        amcProductExtAtomSV.deleteAmcProductExt(tenantId, productId,
                AmcConstants.AmcProductInfo.AttrName.BILL_SUBJECT_CODE);
        AmcProductExt record = new AmcProductExt();
        record.setTenantId(tenantId);
        record.setProductId(productId);
        for (String subjectId : billSubjectList) {
            record.setExtName(AmcConstants.AmcProductInfo.AttrName.BILL_SUBJECT_CODE);
            record.setExtValue(subjectId);
            amcProductExtAtomSV.saveAmcProductExt(record);
        }
    }

}
