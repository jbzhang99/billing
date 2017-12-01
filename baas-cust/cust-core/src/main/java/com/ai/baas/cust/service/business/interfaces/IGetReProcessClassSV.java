package com.ai.baas.cust.service.business.interfaces;

import java.util.List;

import com.ai.baas.cust.dao.mapper.bo.PubServiceRoute;
import com.ai.baas.cust.service.processor.IDeductProcessor;

public interface IGetReProcessClassSV {
	IDeductProcessor getProcessor(String tenantId, String serviceType);
}
