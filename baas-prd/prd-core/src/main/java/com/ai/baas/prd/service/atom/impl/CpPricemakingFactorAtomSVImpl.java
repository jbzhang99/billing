package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingFactor;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingFactorCriteria;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingFactorCriteria.Criteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.ICpPricemakingFactorAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class CpPricemakingFactorAtomSVImpl implements ICpPricemakingFactorAtomSV {

	@Override
	public int addCpPricemakingFactor(CpPricemakingFactor record)
			throws SystemException {
		return MapperFactory.getCpPricemakingFactorMapper().insertSelective(record);
	}

	@Override
	public List<CpPricemakingFactor> queryPricemakingFactorByPriceProductId(String priceProductId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delCpPricemakingFactorByProductId(String tenantId,String priceProductId) throws SystemException {
		CpPricemakingFactorCriteria example = new CpPricemakingFactorCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andPriceProductIdEqualTo(priceProductId);
		return MapperFactory.getCpPricemakingFactorMapper().deleteByExample(example);
	}

	
	
	
}
