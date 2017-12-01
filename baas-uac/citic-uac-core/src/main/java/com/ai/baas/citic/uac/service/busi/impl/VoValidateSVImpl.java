package com.ai.baas.citic.uac.service.busi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.citic.uac.api.citicuser.param.UserQueryRequest;
import com.ai.baas.citic.uac.constants.AccountExceptCode;
import com.ai.baas.citic.uac.service.atom.interfaces.ILoginAtomSV;
import com.ai.baas.citic.uac.service.busi.interfaces.ISysUserValidateSV;
import com.ai.baas.citic.uac.service.busi.interfaces.IVoValidateSV;
import com.ai.opt.base.exception.BusinessException;

@Component
public class VoValidateSVImpl implements IVoValidateSV {


    @Autowired
    ISysUserValidateSV iSysUserValidateSV;

    @Autowired
    ILoginAtomSV iLoginAtomSV;


    @Override
    public void validateLogin(String username) throws BusinessException {

        iSysUserValidateSV.checkUserName(username);
    }


    @Override
    public void validateQueryAccountBaseInfo(UserQueryRequest accountQueryRequest)
            throws BusinessException {
        if (accountQueryRequest == null) {
            throw new BusinessException(AccountExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
        iSysUserValidateSV.checkUserId(accountQueryRequest.getUserId());
    }


}
