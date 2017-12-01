package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.amc.api.fundquery.param.SpecialFundBookQueryRequest;
import com.ai.opt.base.exception.BusinessException;

/**
 * 账本查询业务接口定义
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IFundQueryBusiSV {

    /**
     * 账户余额查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    FundBookQueryResponse queryFund(FundBookQueryRequest request) throws BusinessException;
    
    /**
     * 可用余额查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    FundBookQueryResponse queryUsableFund(FundBookQueryRequest request) throws BusinessException;
    
    /**
     * 账户下特定科目余额查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    FundBookQueryResponse queryFundBySubjectId(SpecialFundBookQueryRequest request) throws BusinessException;
    
}
