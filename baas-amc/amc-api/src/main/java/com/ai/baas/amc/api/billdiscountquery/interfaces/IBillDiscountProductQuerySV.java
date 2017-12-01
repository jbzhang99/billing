package com.ai.baas.amc.api.billdiscountquery.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryResponse;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单优惠产品查询服务<br>
 * 
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author fanpw
 */

@Path("/billDiscountProductQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillDiscountProductQuerySV {
    /**
     * 账单优惠活动产品列表分页查询
     * 
     * @param request 账单优惠活动产品列表查询请求
     * @return 账单优惠活动产品列表查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0029
     * @RestRelativeURL billDiscountProductQuery/queryBillDiscountProductList
     */
    @POST
    @Path("/queryBillDiscountProductList")
    BillDiscountProductListQueryResponse queryBillDiscountProductList(
            BillDiscountProductListQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 账单优惠活动产品查询
     * 
     * @param request 账单优惠活动产品查询请求
     * @return 账单优惠活动产品信息查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0030
     * @RestRelativeURL billDiscountProductQuery/queryBillDiscountProduct
     */
    @POST
    @Path("/queryBillDiscountProduct")
    BillDiscountProductQueryResponse queryBillDiscountProduct(
            BillDiscountProductQueryRequest request) throws BusinessException, SystemException;

}
