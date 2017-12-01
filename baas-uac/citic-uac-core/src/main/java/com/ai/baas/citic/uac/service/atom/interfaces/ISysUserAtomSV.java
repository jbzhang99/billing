package com.ai.baas.citic.uac.service.atom.interfaces;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.opt.base.exception.SystemException;

public interface ISysUserAtomSV {
	
	SysUser queryByUserId(String userId) throws SystemException;
	
}
