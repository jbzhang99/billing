package com.ai.baas.amc.api.oweinfoquery.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryResponse;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 欠费查询服务<br>
 * 
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author fanpw
 */
@Path("/oweInfoQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IOweInfoQuerySV {

    /**
     * 欠费分页查询
     * 
     * @param request 欠费列表查询请求
     * @return 欠费列表查询返回结果
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0031
     * @RestRelativeURL oweInfoQuery/queryOweInfoList
     */
    @POST
    @Path("/queryOweInfoList")
    OweInfoListQueryResponse queryOweInfoList(
            OweInfoListQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 欠费明细查询
     * 
     * @param request 欠费明细查询请求
     * @return 欠费明细查询返回报文
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0032
     * @RestRelativeURL oweInfoQuery/queryOweDetailInfo
     */
    @POST
    @Path("/queryOweDetailInfo")
    OweDetailInfoQueryResponse queryOweDetailInfo(
            OweDetailInfoQueryRequest request) throws BusinessException, SystemException;

}
