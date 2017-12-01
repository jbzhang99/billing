package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductDeleteRequest;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.opt.base.exception.BusinessException;

/**
 * 账单优惠管理业务层服务定义
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IBillDiscountProductMaintainBusiSV {

    /**
     * 新增账单优惠产品.<br>
     * @param vo
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    String addBillDiscountProduct(BillDiscountProductVo vo) throws BusinessException;
    
    /**
     * 删除指定账单优惠活动产品
     * @param request
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    void deleteBillDiscountProduct(BillDiscountProductDeleteRequest request) throws BusinessException;
    
    /**
     * 更新账单优惠产品
     * @param vo
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    void updateBillDiscountProduct(BillDiscountProductUpdateVo vo) throws BusinessException;
    
}
