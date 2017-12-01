package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceInfo;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpCunitpriceInfoAtom;
@Component("cpCunitpriceInfoAtom")
public class CpCunitpriceInfoAtomImpl implements ICpCunitpriceInfoAtom{

	@Override
	public void insert(CpCunitpriceInfo cpCunitpriceInfo) {
		MapperFactory.getCpCunitpriceInfoMapper().insert(cpCunitpriceInfo);
	}

}
