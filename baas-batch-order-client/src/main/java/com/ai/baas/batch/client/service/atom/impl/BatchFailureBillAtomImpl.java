package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.BatchFailureBill;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.IBatchFailureBillAtom;
@Component("batchFailureBill")
public class BatchFailureBillAtomImpl implements IBatchFailureBillAtom{

	@Override
	public void insert(BatchFailureBill batchFailureBill) {
		// TODO Auto-generated method stub
		MapperFactory.getBatchFailureBillMapper().insert(batchFailureBill);
	}

}
