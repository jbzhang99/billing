package com.ai.baas.amc.api.oweinfo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.amc.api.oweinfo.interfaces.IOweInfoSV;
import com.ai.baas.amc.api.oweinfo.params.OweInfoCreateRequest;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IOweInfoBusiSV;
import com.ai.baas.amc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OweInfoSVImpl implements IOweInfoSV {
    @Autowired
    private transient IOweInfoBusiSV iOweInfoBusiSV;

    @Override
    public BaseResponse createOweInfo(OweInfoCreateRequest request) throws BusinessException,
            SystemException {
        BusinessUtil.checkBaseInfo(request);
        if (StringUtil.isBlank(request.getAcctId())) {
            throw new BusinessException("账户标识为空");
        }
        if (StringUtil.isBlank(request.getCustId())) {
            throw new BusinessException("客户标识为空");
        }
        if (StringUtil.isBlank(request.getCustName())) {
            throw new BusinessException("客户名称为空");
        }
        iOweInfoBusiSV.createOweInfo(request);
        BaseResponse response = new BaseResponse();
        response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
        return response;
    }

}
