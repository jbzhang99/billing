package com.ai.baas.dst.api.discountcal.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.dst.api.discountcal.params.DiscountCalRequest;
import com.ai.baas.dst.api.discountcal.params.DiscountCalResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 优惠活动计算服务
 * @author wangjing19
 *
 */
@Path("/discountCal")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IDiscountCalSV {
    
    /**
     * 优惠活动计算
     * 
     * @param 优惠活动计算服务入参
     * @return 优惠活动计算服务返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author wangjing19
     * @ApiDocMethod
     * @ApiCode DST_0001
     * @RestRelativeURL discountCal/getResultByDiscountCal
     */
    @POST
    @Path("/getResultByDiscountCal")
    public DiscountCalResponse getResultByDiscountCal(DiscountCalRequest request) throws BusinessException,SystemException;

}
