package com.ai.baas.amc.api.paymentquery.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 缴费查询服务.<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */

@Path("/paymentQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IPaymentQuerySV {
    
    /**
     * 缴费记录查询
     * @param request 充值缴费列表查询请求
     * @return 充值缴费列表查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0033
     * @RestRelativeURL paymentQuery/queryPaymentLog
     */
    @POST
    @Path("/queryPaymentLog")
    PaymentLogQueryResponse queryPaymentLog(PaymentLogQueryRequest request) throws BusinessException, SystemException;
   
}
