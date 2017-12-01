package com.ai.baas.amc.api.paymentquery.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.paymentquery.interfaces.IPaymentQuerySV;
import com.ai.baas.amc.api.paymentquery.param.PaymentLog;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryResponse;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IPaymentQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 缴费查询服务.<br>
 *
 * Date: 2016年4月17日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Component
public class PaymentQuerySVImpl implements IPaymentQuerySV {

    private static final Logger LOG = LogManager.getLogger(PaymentQuerySVImpl.class);
    
    @Autowired
    private IPaymentQueryBusiSV paymentQueryBusiSV;
    
    @Override
    public PaymentLogQueryResponse queryPaymentLog(PaymentLogQueryRequest request)
            throws BusinessException, SystemException {
        LOG.info("缴费查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "欠费分页查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (request.getPageInfo() == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取参数失败:分页信息不能为空");
        }
        if (request.getPageInfo().getPageNo() == null || request.getPageInfo().getPageNo() == 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取参数失败:查询页码不能为空");
        }
        if (request.getPageInfo().getPageSize() == null || request.getPageInfo().getPageSize() == 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取参数失败:每页条数不能为空");
        }
        /*
        if (request.getStartTime() == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "缴费查询开始时间不能为空");
        }
        if (request.getEndTime() == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "缴费查询结束时间不能为空");
        }
        */
        if(request.getStartTime() != null && request.getEndTime() != null) {
            if(request.getStartTime().compareTo(request.getEndTime()) > 0) {
                throw new BusinessException(ExceptCodeConstants.PARAM_IS_WRONG, "缴费查询开始时间不能大于结束时间！");
            }
            String startMonth = DateUtil.getDateString(request.getStartTime(), DateUtil.YYYYMM);
            String endMonth = DateUtil.getDateString(request.getEndTime(), DateUtil.YYYYMM);
            if(!startMonth.equals(endMonth)) {
                throw new BusinessException(ExceptCodeConstants.PARAM_IS_WRONG, "缴费记录不能跨月查询！");
            }
        }
        if(request.getBottomAmount() != null && request.getTopAmount() != null) {
            if(request.getBottomAmount() > request.getTopAmount()) {
                throw new BusinessException(ExceptCodeConstants.PARAM_IS_WRONG, "最低充值金额不能大于最高充值金额！");
            }
        }
        PageInfo<PaymentLog> pageInfo = paymentQueryBusiSV.queryPaymentLog(request);
        PaymentLogQueryResponse response = new PaymentLogQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setPageInfo(pageInfo);
        response.setResponseHeader(responseHeader);
        LOG.info("缴费查询结束");
        return response;
    }

}
