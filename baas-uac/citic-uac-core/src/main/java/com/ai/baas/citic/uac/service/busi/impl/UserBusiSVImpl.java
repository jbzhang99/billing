package com.ai.baas.citic.uac.service.busi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.baas.citic.uac.service.atom.interfaces.ISysUserAtomSV;
import com.ai.baas.citic.uac.service.busi.interfaces.IUserBusiSV;
import com.ai.opt.base.exception.SystemException;

@Service
@Transactional
public class UserBusiSVImpl implements IUserBusiSV {

    @Autowired
    ISysUserAtomSV iSysUserAtomSV;

    @Override
    public SysUser queryByUserId(String userId) throws SystemException {
        return iSysUserAtomSV.queryByUserId(userId);
    }

    

}
