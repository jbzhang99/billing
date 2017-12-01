package com.ai.baas.amc.api.billdiscount.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductDeleteRequest;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductMaintainResponse;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠管理服务<br>
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Path("/billDiscountProductMaintain")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillDiscountProductMaintainSV {
    
    /**
     * 新增账单优惠产品
     * 
     * @param vo 账单优惠产品新增入参
     * @return 账单优惠维护返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0026
     * @RestRelativeURL billDiscountProductMaintain/addBillDiscountProduct
     */
    @POST
    @Path("/addBillDiscountProduct")
    BillDiscountProductMaintainResponse addBillDiscountProduct(BillDiscountProductVo vo) throws BusinessException, SystemException;
    
    /**
     * 删除账单优惠产品
     * 
     * @param request 删除指定账单优惠活动产品请求
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0027
     * @RestRelativeURL billDiscountProductMaintain/deleteBillDiscountProduct
     */
    @POST
    @Path("/deleteBillDiscountProduct")
    BaseResponse deleteBillDiscountProduct(BillDiscountProductDeleteRequest request) throws BusinessException, SystemException;
    
    /**
     * 更新账单优惠产品
     * 
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0028
     * @RestRelativeURL billDiscountProductMaintain/updateBillDiscountProduct
     */
    @POST
    @Path("/updateBillDiscountProduct")
    BaseResponse updateBillDiscountProduct(BillDiscountProductUpdateVo vo) throws BusinessException, SystemException;
}
