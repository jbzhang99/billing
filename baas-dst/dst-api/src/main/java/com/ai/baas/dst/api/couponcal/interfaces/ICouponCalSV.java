package com.ai.baas.dst.api.couponcal.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.dst.api.couponcal.params.CouponCalRequest;
import com.ai.baas.dst.api.couponcal.params.CouponCalResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 优惠卷计算服务
 * @author wangjing19
 *
 */
@Path("/couponCal")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICouponCalSV {
    
    /**
     * 优惠卷计算
     * 
     * @param 优惠卷计算服务入参
     * @return 优惠卷计算服务返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author wangjing19
     * @ApiDocMethod
     * @ApiCode DST_0001
     * @RestRelativeURL couponCal/getResultByCouponCal
     */
    @POST
    @Path("/getResultByCouponCal")
    public CouponCalResponse getResultByCouponCal(CouponCalRequest request);

}
