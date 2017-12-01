package com.ai.baas.dst.service.business.interfaces;

import java.util.List;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountInfo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠产品查询业务接口定义
 * @author wangluyang
 *
 */
public interface IBillDiscountQueryBusiSV {

    /**
     * 账单优惠活动产品列表分页查询
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<BillDiscountInfo> queryBillDiscountList(
            BillDiscountListQueryRequest request) throws BusinessException;
    
    /**
     * 账单优惠活动产品列表查询
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    List<BillDiscountInfo> queryDiscountList(
    		BillDiscountListQueryRequest request) throws BusinessException;
    
    /**
     * 账单优惠活动产品查询
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    BillDiscountInfo queryBillDiscountProduct(
            BillDiscountQueryRequest request) throws BusinessException;
    
    /**
     * 
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    List<String> queryEffectiveScopeProduct(EffectiveProductQueryRequest request)
    		throws BusinessException;
    
    /**
     * 
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
    List<String> queryEffectiveCoupon(BaseInfo request)
    		throws BusinessException;
}
