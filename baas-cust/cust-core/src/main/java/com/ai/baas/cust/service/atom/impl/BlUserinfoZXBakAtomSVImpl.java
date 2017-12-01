package com.ai.baas.cust.service.atom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.cust.dao.interfaces.BlUserinfoZxBakMapper;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZxBak;
import com.ai.baas.cust.service.atom.interfaces.IBlUserinfoZXBakAtomSV;
@Component
public class BlUserinfoZXBakAtomSVImpl implements IBlUserinfoZXBakAtomSV {
	@Autowired
    private BlUserinfoZxBakMapper blUserinfoZxBakMapper;
	@Override
	public void addUserInfoBak(BlUserinfoZxBak bak) {
		
		blUserinfoZxBakMapper.insert(bak);
	}

}
