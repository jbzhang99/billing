package com.ai.baas.citic.uac.service.busi.interfaces;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.opt.base.exception.BusinessException;

public interface ILoginBusiSV {
    SysUser queryByUserName(SysUser user) throws BusinessException;
    
}
