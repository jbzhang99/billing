package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMapping;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMappingCriteria;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMappingCriteria.Criteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.ICpPricemakingMappingAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class CpPricemakingMappingAtomSVImpl implements ICpPricemakingMappingAtomSV {

	
	@Override
	public List<CpPricemakingMapping> queryAllPricemakingMappingRule() throws SystemException {
		CpPricemakingMappingCriteria example = new CpPricemakingMappingCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andIsValidEqualTo("Y");
		return MapperFactory.getCpPricemakingMappingMapper().selectByExample(example);
	}

	
}
