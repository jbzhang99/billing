package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductInfo;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠产品查询业务接口定义
 *
 * Date: 2016年4月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IBillDiscountProductQueryBusiSV {

    /**
     * 账单优惠活动产品列表分页查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<BillDiscountProductInfo> queryBillDiscountProductList(
            BillDiscountProductListQueryRequest request) throws BusinessException;
    
    /**
     * 账单优惠活动产品查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    BillDiscountProductInfo queryBillDiscountProduct(
            BillDiscountProductQueryRequest request) throws BusinessException;
    
}
