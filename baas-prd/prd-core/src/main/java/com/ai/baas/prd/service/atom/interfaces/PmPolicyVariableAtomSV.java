package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariable;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariableCriteria;

public interface PmPolicyVariableAtomSV  {

	public void add(PmPolicyVariable variable);
	
	public int deleteByExample(PmPolicyVariableCriteria example);
	
	public List<PmPolicyVariable> selectByExample(PmPolicyVariableCriteria example);

    public List<PmPolicyVariable> queryPolicyByPolicy(String pricePolicy, String tenantId, String varCode);
    
    public List<PmPolicyVariable> queryVariablesByPolicyId(String pricePolicy, String tenantId);
    
}
