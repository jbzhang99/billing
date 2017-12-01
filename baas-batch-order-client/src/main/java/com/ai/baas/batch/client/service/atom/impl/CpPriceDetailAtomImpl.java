package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpPriceDetail;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceDetailAtom;
@Component("cpPriceDetailAtom")
public class CpPriceDetailAtomImpl implements ICpPriceDetailAtom{

	@Override
	public void insert(CpPriceDetail cpPriceDetail) {
		MapperFactory.getCpPriceDetailMapper().insert(cpPriceDetail);
	}
	
}
