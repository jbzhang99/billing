package com.ai.baas.batch.client.service.business.interfaces;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;

public interface IServiceInfoSV {

	public PmBasedataCode getBasedataCode(String tenantId, String serviceId);
}
