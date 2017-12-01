package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpFactorInfo;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpFactorInfoAtom;
@Component("cpFactorinfoAtom")
public class CpFactorInfoAtomImpl implements ICpFactorInfoAtom{

	@Override
	public void insert(CpFactorInfo cpFactorInfo) {
		MapperFactory.getCpFactorInfoMapper().insert(cpFactorInfo);
	}

}
