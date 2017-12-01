package com.ai.baas.batch.client.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;
import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCodeCriteria;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.IPmBaseDataCodeAtom;
@Component("batchBaseCode")
public class PmBaseDataCodeAtomImpl implements IPmBaseDataCodeAtom{

	@Override
	public List<PmBasedataCode> queryPmBaseData(String tenantId, String serviceId) {
		PmBasedataCodeCriteria pmBasedataCodeCriteria = new PmBasedataCodeCriteria();
		PmBasedataCodeCriteria.Criteria criteria = pmBasedataCodeCriteria.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andParamCodeEqualTo(serviceId);
		List<PmBasedataCode> pmBasedataCodes = MapperFactory.getPmBasedataCodeMapper().selectByExample(pmBasedataCodeCriteria);

		return pmBasedataCodes;
	}

}
