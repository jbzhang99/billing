package com.ai.baas.amc.api.custbalancequery.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 可用余额查询服务（客户级别）.<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */

@Path("/custBalanceQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICustBalanceQuerySV {
    
    /**
     * 可用余额分页查询（客户级别）
     * @param request 可用余额查询请求参数
     * @return 可用余额分页查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0034
     * @RestRelativeURL custBalanceQuery/queryUsableBalance
     */

    @POST
    @Path("/queryUsableBalance")
    UsableBalanceQueryResponse queryUsableBalance(UsableBalanceQueryRequest request) throws BusinessException, SystemException;
   
}
