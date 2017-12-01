package com.ai.baas.citic.uac.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.baas.citic.uac.dao.mapper.factory.MapperFactory;
import com.ai.baas.citic.uac.dao.mapper.interfaces.SysUserMapper;
import com.ai.baas.citic.uac.service.atom.interfaces.ISysUserAtomSV;
import com.ai.opt.base.exception.SystemException;
@Component
public class SysUserAtomSVImpl implements ISysUserAtomSV {

	@Override
	public SysUser queryByUserId(String userId) throws SystemException {
		SysUserMapper gnAccountlMapper = MapperFactory.getSysUserMapper();
		return gnAccountlMapper.selectByPrimaryKey(userId);
	}

}
