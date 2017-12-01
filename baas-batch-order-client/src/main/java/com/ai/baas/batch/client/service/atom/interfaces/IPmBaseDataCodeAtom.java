package com.ai.baas.batch.client.service.atom.interfaces;

import java.util.List;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;

public interface IPmBaseDataCodeAtom {

	public List<PmBasedataCode> queryPmBaseData(String tenantId, String serviceId);
}
