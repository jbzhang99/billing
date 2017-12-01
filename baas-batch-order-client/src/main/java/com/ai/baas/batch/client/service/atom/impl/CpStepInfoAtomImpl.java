package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfo;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpStepInfoAtom;
@Component("cpStepInfoAtom")
public class CpStepInfoAtomImpl implements ICpStepInfoAtom{

	@Override
	public void insert(CpStepInfo cpStepInfo) {
		MapperFactory.getCpStepInfoMapper().insert(cpStepInfo);
	}

}
