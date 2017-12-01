package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceInfoAtom;
@Component("cpPriceInfoAtom")
public class CpPriceInfoAtomImpl implements ICpPriceInfoAtom{

	@Override
	public void insert(CpPriceInfo cpPriceInfo) {
		MapperFactory.getCpPriceInfoMapper().insert(cpPriceInfo);
	}
	
}
