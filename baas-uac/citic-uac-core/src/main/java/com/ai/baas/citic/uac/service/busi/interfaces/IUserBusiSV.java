package com.ai.baas.citic.uac.service.busi.interfaces;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.opt.base.exception.SystemException;

public interface IUserBusiSV {
	
	SysUser queryByUserId(String userId) throws SystemException;
	
}
