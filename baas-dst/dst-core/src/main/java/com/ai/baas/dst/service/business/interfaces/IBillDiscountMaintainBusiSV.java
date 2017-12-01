package com.ai.baas.dst.service.business.interfaces;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountDeleteRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.opt.base.exception.BusinessException;

/**
 * 账单优惠管理业务层服务定义
 * @author wangluyang
 *
 */
public interface IBillDiscountMaintainBusiSV {

    /**
     * 新增账单优惠产品.<br>
     * @param vo
     * @return
     * @throws BusinessException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     */
    String addBillDiscountProduct(BillDiscountVo vo) throws BusinessException;
    
    /**
     * 删除指定账单优惠活动产品
     * @param request
     * @throws BusinessException
     */
    void deleteBillDiscountProduct(BillDiscountDeleteRequest request) throws BusinessException;
    
    /**
     * 更新账单优惠产品
     * @param vo
     * @throws BusinessException
     */
    void updateBillDiscountProduct(BillDiscountUpdateVo vo) throws BusinessException;
}
