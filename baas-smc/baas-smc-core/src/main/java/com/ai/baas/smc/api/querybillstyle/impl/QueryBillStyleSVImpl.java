package com.ai.baas.smc.api.querybillstyle.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.smc.api.querybillstyle.interfaces.IQueryBillStyleSV;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyle;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleListInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleListVoResponse;
import com.ai.baas.smc.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.service.busi.interfaces.IQueryBillStyleBusiSV;
import com.ai.baas.smc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class QueryBillStyleSVImpl implements IQueryBillStyleSV {

    @Autowired
    private IQueryBillStyleBusiSV iQueryBillStyleBusiSV;

    @Override
    public QueryBillStyleInfo queryBillStyle(QueryBillStyle queryBillStyle)
            throws BusinessException {
        // TODO Auto-generated method stub
        BusinessUtil.checkBaseInfo(queryBillStyle);
        if ((queryBillStyle.getBillStyleId() == null) || (queryBillStyle.getBillStyleId() == 0)) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "账单样式ID不可为空");
        }
        return iQueryBillStyleBusiSV.queryBillStyle(queryBillStyle);
    }

    @Override
    public QueryBillStyleListVoResponse queryBillStyleList(
            QueryBillStyleListInfo queryBillStyleListInfo) throws BusinessException {
        // TODO Auto-generated method stub
        BusinessUtil.checkBaseInfo(queryBillStyleListInfo);
        return iQueryBillStyleBusiSV.queryBillStyleList(queryBillStyleListInfo);
    }

}
