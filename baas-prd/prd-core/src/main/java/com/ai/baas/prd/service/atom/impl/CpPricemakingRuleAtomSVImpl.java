package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingRule;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingRuleCriteria;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingRuleCriteria.Criteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.ICpPricemakingRuleAtomSV;
import com.ai.opt.base.exception.SystemException;
@Component
public class CpPricemakingRuleAtomSVImpl implements ICpPricemakingRuleAtomSV {

	@Override
	public int addCpPricemakingRule(CpPricemakingRule record) throws SystemException {
		return MapperFactory.getCpPricemakingRuleMapper().insertSelective(record);
	}

	@Override
	public List<CpPricemakingRule> queryPricemakingRuleByPriceProductId(
			String priceProductId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delCpPricemakingRuleByProductId(String tenantId, String priceProductId) throws SystemException {
		CpPricemakingRuleCriteria example = new CpPricemakingRuleCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andPriceProductIdEqualTo(priceProductId);
		return MapperFactory.getCpPricemakingRuleMapper().deleteByExample(example);
	}
	
	

}
