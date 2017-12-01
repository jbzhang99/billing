package com.ai.baas.amc.api.deduct.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.deduct.param.DeductRequest;
import com.ai.baas.amc.api.deduct.param.DeductResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 余额扣减服务 <br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */

@Path("/deduct")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IDeductSV {

    /**
     * 扣款 (扣减).<br>
     * 处理外部平台向余额中心发起的单次扣款或提现的请求，支持扣减账户的现金、赠款、红包、优惠券<br>
     * 
     * @param request
     * @return 扣款结果返回报文
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0003
     * @RestRelativeURL deduct/deductFund
     */

    @POST
    @Path("/deductFund")
    DeductResponse deductFund(DeductRequest request) throws BusinessException, SystemException;
    
}
