package com.ai.baas.amc.api.queryinoutfundserial.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 收支明细查询<br>
 * 
 * Date: 2016年7月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangjl9
 */
@Path("/queryFundSerial")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IqueryFundSerialSV {

    /**
     * 收支明细查询<br>
     * 
     * @param queryFundSerialRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangjl9
     * @ApiDocMethod
     * @RestRelativeURL queryFundSerial/queryFundSerialList
     */
    @POST
    @Path("/queryFundSerialList")
    QueryFundSerialResponse queryFundSerialList(QueryFundSerialRequest queryFundSerialRequest)
            throws BusinessException, SystemException;
}
