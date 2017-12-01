package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmSpecType;
import com.ai.baas.prd.dao.mapper.bo.PmSpecTypeCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmSpecTypeAtomSV;
@Component
public class PmSpecTypeAtomSVImpl implements IPmSpecTypeAtomSV{

	@Override
	public List<PmSpecType> selectByCategoryId(String CategoryId, String tenantId) {
		// TODO Auto-generated method stub
		
		PmSpecTypeCriteria specTypeCriteria = new PmSpecTypeCriteria();
		PmSpecTypeCriteria.Criteria criterion = specTypeCriteria.createCriteria();
		criterion.andTenantIdEqualTo(tenantId);
		criterion.andCategoryIdEqualTo(CategoryId);
		
		return MapperFactory.getPmSpecTypeMapper().selectByExample(specTypeCriteria);
	}

}
