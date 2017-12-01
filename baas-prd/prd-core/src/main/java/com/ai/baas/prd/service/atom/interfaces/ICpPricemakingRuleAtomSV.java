package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingRule;
import com.ai.opt.base.exception.SystemException;

public interface ICpPricemakingRuleAtomSV {

	public int addCpPricemakingRule(CpPricemakingRule record) throws SystemException;
	
	public List<CpPricemakingRule> queryPricemakingRuleByPriceProductId(String priceProductId)throws SystemException;
	
	public int delCpPricemakingRuleByProductId(String tenantId,String priceProductId)throws SystemException;
}
