package com.ai.baas.bmc.api.detailsquery.interfaces;

import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 详单查询服务
 *
 * Date: 2016年10月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/detailsQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IDetailsQuerySV {

    /**
     * 详单查询
     * @param request
     * @return
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL detailsQuery/queryDetails
     */
    @Path("/queryDetails")
    @POST
    public DetailsQueryResponse queryDetails(DetailsQueryRequest request) throws BusinessException,SystemException;
}
