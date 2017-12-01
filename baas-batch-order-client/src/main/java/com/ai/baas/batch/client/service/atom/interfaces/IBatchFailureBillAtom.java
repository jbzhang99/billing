package com.ai.baas.batch.client.service.atom.interfaces;

import com.ai.baas.batch.client.dao.mapper.bo.BatchFailureBill;

public interface IBatchFailureBillAtom {
	public void insert(BatchFailureBill batchFailureBill);
}
