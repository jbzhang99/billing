package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.BmcOrderLogYyyymm;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.IBmcOrderLogAtom;


@Component("batchOrderLog")
public class BmcOrderLogAtomImpl implements IBmcOrderLogAtom{

	@Override
	public void insertOrderLog(String currentMonth , BmcOrderLogYyyymm bmcOrderLogYyyymm) {
		MapperFactory.getBmcOrderLogYyyymmMapper().insert(currentMonth, bmcOrderLogYyyymm);
	}

}
