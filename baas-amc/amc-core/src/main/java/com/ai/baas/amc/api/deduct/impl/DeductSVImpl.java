package com.ai.baas.amc.api.deduct.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.deduct.interfaces.IDeductSV;
import com.ai.baas.amc.api.deduct.param.DeductRequest;
import com.ai.baas.amc.api.deduct.param.DeductResponse;
import com.ai.baas.amc.api.deduct.param.TransSummary;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IDeductBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;


/**
 * 余额扣减服务 <br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Component
public class DeductSVImpl implements IDeductSV {
    
    private static final Logger LOG = LogManager.getLogger(DeductSVImpl.class);
    
    @Autowired
    private IDeductBusiSV deductBusiSV;

    /**
     * 扣款 (扣减)
     */
    @Override
    public DeductResponse deductFund(DeductRequest request) throws BusinessException,
            SystemException {
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getSystemId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "系统ID不能为空");
        }
        if (StringUtil.isBlank(request.getAccountId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账户号不能为空");
        }
        if (StringUtil.isBlank(request.getExternalId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "外部流水号不能为空");
        }
        if (StringUtil.isBlank(request.getBusinessCode())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "业务操作类型不能为空");
        }
        if (request.getTotalAmount() <= 0l) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "扣款总金额必须大于零");
        }
        if (!CollectionUtil.isEmpty(request.getTransSummary())) {
            for (TransSummary summary : request.getTransSummary()) {
                if (summary.getSubjectId() == 0l) {
                    throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL,
                            "资金科目ID不能为空[" + JSON.toJSONString(summary) + "]");
                }
            }
        }
        
        String paySerialCode = deductBusiSV.deductFund(request);
        DeductResponse response = new DeductResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setPaySerialCode(paySerialCode);
        response.setResponseHeader(responseHeader);
        return response;
    }

}
