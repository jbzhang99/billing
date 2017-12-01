package com.ai.baas.amc.api.fundquery.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.amc.api.fundquery.param.SpecialFundBookQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 账户资金查询接口<br>
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */

@Path("/fundQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IFundQuerySV {
    
    /**
     * 账户余额查询.<br>
     * 按账户ID查询账户余额：查询账户下的资金账本，含现金、通信现金、赠款等之和，包括可用金额＋冻结金之和。不包括红包，优惠券，押金<br>
     * 
     * @param request 余额查询请求
     * @return 账户余额
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0008
     * @RestRelativeURL fundQuery/queryFund
     */
    @POST
    @Path("/queryFund")
    FundBookQueryResponse queryFund(FundBookQueryRequest request) throws BusinessException, SystemException;

    /**
     * 可用余额查询.<br>
     * 按账户ID查询账户可用余额：查询账户下的可用余额，含现金、通信现金、赠款及明细查询。不包括红包，优惠券，押金<br>
     * 
     * @param request 余额查询请求
     * @return 账户可用余额
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0009
     * @RestRelativeURL fundQuery/queryUsableFund
     */
    @POST
    @Path("/queryUsableFund")
    FundBookQueryResponse queryUsableFund(FundBookQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 按照科目和账户查询资金账本.<br>
     * 
     * @param request 余额查询请求
     * @return 账户下特定科目余额
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode AMC_0010
     * @RestRelativeURL fundQuery/queryFundBySubjectId
     */
    @POST
    @Path("/queryFundBySubjectId")
    FundBookQueryResponse queryFundBySubjectId(SpecialFundBookQueryRequest request) throws BusinessException, SystemException;
}
