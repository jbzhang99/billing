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
 * 限时打折优惠具体实现类
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component(value = "DZBillDiscountProductManager")
public class DZBillDiscountProductManager extends AbstractBillDiscountProductManager {
    
    private static final Logger LOG = LogManager.getLogger(DZBillDiscountProductManager.class);

    @Override
    public void validateBillDiscountProductVo(BillDiscountProductVo vo) throws BusinessException {
        if (vo.getDiscountPercent() <= 0 || vo.getDiscountPercent() >= 1) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "传入的折扣比例有误");
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
        detailInfo.setCalcCondition(AmcConstants.AmcProductInfo.PREFERENTIAL_TERMS_CODE);
        detailInfo.setCalcType(AmcConstants.AmcProductInfo.CalcType.CALC_TYPE_DZ);
        amcProductDetailAtomSV.saveAmcProductDetail(detailInfo);
        LOG.info("保存账务优惠产品明细信息结束：OK");
    }

    @Override
    public Map<String, String> assembleExtendInfo(String productId, BillDiscountProductVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.DISCOUNT_PERCENT, String.valueOf(vo.getDiscountPercent()));
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
        if (vo.getDiscountPercent() != null && vo.getDiscountPercent() < 0
                && vo.getDiscountPercent() >= 1) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "传入的折扣比例有误");
        }
    }

    @Override
    public void updateBillDiscountProductDetail(BillDiscountProductUpdateVo vo) {
        
    }

    @Override
    public Map<String, String> assembleExtendInfo(BillDiscountProductUpdateVo vo) {
        Map<String, String> extendInfoMap = new HashMap<String, String>();
        if(vo.getDiscountPercent() != null) {
            extendInfoMap.put(AmcConstants.AmcProductInfo.AttrName.DISCOUNT_PERCENT, String.valueOf(vo.getDiscountPercent()));
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
