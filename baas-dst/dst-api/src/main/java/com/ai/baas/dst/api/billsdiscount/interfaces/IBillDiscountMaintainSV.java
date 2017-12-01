package com.ai.baas.dst.api.billsdiscount.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountDeleteRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountMaintainResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠管理服务
 * @author wangluyang
 *
 */
@Path("/billDiscountMaintain")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillDiscountMaintainSV {
    
    /**
     * 新增账单优惠产品
     * 
     * @param vo 账单优惠产品新增入参
     * @return 账单优惠维护返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0001
     * @RestRelativeURL billDiscountMaintain/addBillDiscountProduct
     */
    @POST
    @Path("/addBillDiscountProduct")
    BillDiscountMaintainResponse addBillDiscountProduct(BillDiscountVo vo) throws BusinessException, SystemException;
    
    /**
     * 删除账单优惠产品
     * 
     * @param request 删除指定账单优惠活动产品请求
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0002
     * @RestRelativeURL billDiscountMaintain/deleteBillDiscountProduct
     */
    @POST
    @Path("/deleteBillDiscountProduct")
    BaseResponse deleteBillDiscountProduct(BillDiscountDeleteRequest request) throws BusinessException, SystemException;
    
    /**
     * 更新账单优惠产品
     * 
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode DST_0003
     * @RestRelativeURL billDiscountMaintain/updateBillDiscountProduct
     */
    @POST
    @Path("/updateBillDiscountProduct")
    BaseResponse updateBillDiscountProduct(BillDiscountUpdateVo vo) throws BusinessException, SystemException;
}
