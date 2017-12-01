package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactor;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactorCriteria;

public interface PmPolicyFactorAtomSV  {

	public void add(PmPolicyFactor factor);
	
	public int deleteByExample(PmPolicyFactorCriteria example);
	
	public List<PmPolicyFactor> selectByExample(PmPolicyFactorCriteria example);

    List<PmPolicyFactor> queryPolicyByDetailId(String detailId, String tenantId);

	PmPolicyFactor getFactorById(String id);
	
	List<PmPolicyFactor> queryPolicyByDetailIds(List<String> detailIds, String tenantId);
}
