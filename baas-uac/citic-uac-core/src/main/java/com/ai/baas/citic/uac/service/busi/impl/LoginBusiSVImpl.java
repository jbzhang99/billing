package com.ai.baas.citic.uac.service.busi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.baas.citic.uac.service.atom.interfaces.ILoginAtomSV;
import com.ai.baas.citic.uac.service.busi.interfaces.ILoginBusiSV;
import com.ai.opt.base.exception.BusinessException;

@Service
@Transactional
public class LoginBusiSVImpl implements ILoginBusiSV {
    @Autowired
    ILoginAtomSV iLoginAtomSV;

    @Override
    public SysUser queryByUserName(SysUser user) throws BusinessException {

        return iLoginAtomSV.queryByUserName(user);

    }

}
