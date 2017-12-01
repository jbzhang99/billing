package com.ai.baas.batch.client.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.service.atom.interfaces.IBlUserinfoZxAtom;
import com.ai.baas.batch.client.service.business.interfaces.ICheckInstanceIdSV;
@Component
public class CheckInstanceIdSVImpl implements ICheckInstanceIdSV{

	@Autowired
	@Qualifier("blUserinfoZxAtom") 
	IBlUserinfoZxAtom blUserinfoZxAtomImpl;

	@Override
	public boolean checkDuplicate(String instanceId) {
		if(blUserinfoZxAtomImpl.getCountQuery(instanceId)!=0){
			return false;
		}
		return true;
	}
	
}
