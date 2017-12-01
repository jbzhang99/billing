package com.ai.baas.amc.api.fundquery.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.fundquery.interfaces.IFundQuerySV;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.amc.api.fundquery.param.SpecialFundBookQueryRequest;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IFundQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 账户资金查询接口.<br>
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Component
public class FundQuerySVImpl implements IFundQuerySV {

    private static final Logger LOG = LogManager.getLogger(FundQuerySVImpl.class);
    
    @Autowired
    private IFundQueryBusiSV fundQueryBusiSV;
    
    @Override
    public FundBookQueryResponse queryFund(FundBookQueryRequest request) throws BusinessException,
            SystemException {
        LOG.info("账户余额查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAccountId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账户ID不能为空");
        }
        
        return fundQueryBusiSV.queryFund(request);
    }

    @Override
    public FundBookQueryResponse queryUsableFund(FundBookQueryRequest request)
            throws BusinessException, SystemException {
        LOG.info("账户可用余额查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAccountId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账户ID不能为空");
        }
        return fundQueryBusiSV.queryUsableFund(request);
    }

    @Override
    public FundBookQueryResponse queryFundBySubjectId(SpecialFundBookQueryRequest request)
            throws BusinessException, SystemException {
        LOG.info("查询账户下特定科目余额开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAccountId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账户ID不能为空");
        }
        if (request.getSubjectId() == 0l) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "科目ID不能为空");
        }
        return fundQueryBusiSV.queryFundBySubjectId(request);
    }

}
