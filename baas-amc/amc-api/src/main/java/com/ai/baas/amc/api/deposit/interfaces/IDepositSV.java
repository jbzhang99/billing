package com.ai.baas.amc.api.deposit.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.baas.amc.api.deposit.param.DepositResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 余额存入服务 <br>
 * 
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 AILK <br>
 * 
 * @author fanpw
 */
@Path("/deposit")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IDepositSV {

    /**
     * 存款 (存入).<br>
     * 处理外部平台向余额中心发起的存款的请求，支持存入现金、赠款等<br>
     * 
     * @param request 现金存入请求参数
     * @return 存款结果返回报文，存款成功返回交易流水号
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0002
     * @RestRelativeURL deposit/depositFund
     */
    @POST
    @Path("/depositFund")
    DepositResponse depositFund(DepositRequest request) throws BusinessException, SystemException;

}
