package com.ai.baas.bmc.topology.billing.output;

import com.ai.baas.bmc.topology.entity.BillingDetailRecord;

public interface IOutput {

	//void execute(List<BillingDetailRecord> detailRecords);
	
	void send(BillingDetailRecord detailRecord);
	
}
