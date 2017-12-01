package com.ai.baas.citic.uac.service.busi.interfaces;

import com.ai.opt.base.exception.BusinessException;

public interface ISysUserValidateSV {

	void checkUserId(String accountId) throws BusinessException;

	void checkUserName(String userName) throws BusinessException;

}
