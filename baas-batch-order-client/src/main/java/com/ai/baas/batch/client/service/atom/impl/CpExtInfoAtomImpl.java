package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpExtInfo;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpExtInfoAtom;
@Component("cpExtInfoAtom") 
public class CpExtInfoAtomImpl implements ICpExtInfoAtom{

	@Override
	public void insert(CpExtInfo cpExtInfo) {
		MapperFactory.getCpExtInfoMapper().insert(cpExtInfo);
	}

}
