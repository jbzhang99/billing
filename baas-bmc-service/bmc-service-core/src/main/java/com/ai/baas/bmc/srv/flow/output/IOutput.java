package com.ai.baas.bmc.srv.flow.output;

import com.ai.baas.bmc.srv.entity.BillingDetailRecord;
import com.ai.baas.bmc.srv.failbill.BusinessException;


public interface IOutput {
	
	void send(BillingDetailRecord detailRecord)throws BusinessException;
	
}
