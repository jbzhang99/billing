package com.ai.baas.smc.api.billdata.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.smc.api.billdata.interfaces.IBillDataSV;
import com.ai.baas.smc.api.billdata.param.QueryBillDataDetailRequest;
import com.ai.baas.smc.api.billdata.param.QueryBillDataRequest;
import com.ai.baas.smc.api.billdata.param.QueryBillDataResponse;
import com.ai.baas.smc.api.billdata.param.QueryBillDetailResponse;
import com.ai.baas.smc.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.service.busi.interfaces.IBillDataBusiSV;
import com.ai.baas.smc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class BillDataSVImpl implements IBillDataSV {

    @Autowired
    private IBillDataBusiSV iBillDataBusiSV;

    @Override
    public QueryBillDataResponse queryBillData(QueryBillDataRequest queryBillDataRequest)
            throws BusinessException {
        BusinessUtil.checkBaseInfo(queryBillDataRequest);
        if (StringUtils.isBlank(queryBillDataRequest.getBillTimeSn())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "结算账期不可为空");
        }
        return iBillDataBusiSV.queryBillData(queryBillDataRequest);
    }

    @Override
    public QueryBillDetailResponse queryBillDataDetail(
            QueryBillDataDetailRequest queryBillDataDetailRequest) throws BusinessException {
        BusinessUtil.checkBaseInfo(queryBillDataDetailRequest);
        if ((queryBillDataDetailRequest.getBillId() == null)
                || (queryBillDataDetailRequest.getBillId() == 0)) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "账单ID不可为空");
        }
        if (StringUtils.isBlank(queryBillDataDetailRequest.getBillTimeSn())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "结算账期不可为空");
        }
        return iBillDataBusiSV.queryBillDataDetail(queryBillDataDetailRequest);
    }
}
