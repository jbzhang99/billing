package com.ai.baas.citic.uac.service.busi.interfaces;

import com.ai.baas.citic.uac.api.citicuser.param.UserQueryRequest;
import com.ai.opt.base.exception.BusinessException;

public interface IVoValidateSV {

    /**
     * 登录参数检查
     */
    void validateLogin(String username) throws BusinessException;


    /**
     * 账户详情查询参数检查
     */
    void validateQueryAccountBaseInfo(UserQueryRequest accountQueryRequest)
            throws BusinessException;

}
