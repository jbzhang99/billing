package com.ai.baas.dst.api.billsdiscount.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单优惠产品查询服务
 * @author wangluyang
 *
 */
@Path("/billDiscountQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillDiscountQuerySV {
    /**
     * 账单优惠活动产品列表分页查询
     * 
     * @param request 账单优惠活动产品列表查询请求
     * @return 账单优惠活动产品列表查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0004
     * @RestRelativeURL billDiscountQuery/queryBillDiscountProductList
     */
    @POST
    @Path("/queryBillDiscountProductList")
    BillDiscountListQueryResponse queryBillDiscountProductList(
            BillDiscountListQueryRequest request) throws BusinessException, SystemException;

    /**
     * 账单优惠活动产品查询
     * 
     * @param request 账单优惠活动产品查询请求
     * @return 账单优惠活动产品信息查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0005
     * @RestRelativeURL billDiscountQuery/queryBillDiscountProduct
     */
    @POST
    @Path("/queryBillDiscountProduct")
    BillDiscountQueryResponse queryBillDiscountProduct(
            BillDiscountQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 查询在生效范围内的产品id
     * 
     * @param request 
     * @return 
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0006
     * @RestRelativeURL billDiscountQuery/queryEffectiveScopeProduct
     */
    @POST
    @Path("/queryEffectiveScopeProduct")
    EffectiveProductQueryResponse queryEffectiveScopeProduct(
    		EffectiveProductQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 查询已经参加优惠活动的优惠券id
     * 
     * @param request 
     * @return 
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0007
     * @RestRelativeURL billDiscountQuery/queryEffectiveCoupon
     */
    @POST
    @Path("/queryEffectiveCoupon")
    EffectiveProductQueryResponse queryEffectiveCoupon(
    		BaseInfo request) throws BusinessException, SystemException;
    
    /**
     * 账单优惠活动产品查询不分页
     * 
     * @param request 
     * @return 
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0008
     * @RestRelativeURL billDiscountQuery/queryDiscountList
     */
    @POST
    @Path("/queryDiscountList")
    BillDiscountListResponse queryDiscountList(
    		BillDiscountListQueryRequest request) throws BusinessException, SystemException;
}
