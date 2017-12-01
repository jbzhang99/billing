package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfoCriteria;

public interface PmPolicyInfoAtomSV  {

	public List<PmPolicyInfo> queryPolicy(QueryParams example, List<String> relatedPolicyIds);
	
	public List<PmPolicyInfo> queryPolicyByPolicyId(String policyId,String tenantId);
	
	public PmPolicyInfo selectByPrimaryKey(String policyId);
	
	public void add(PmPolicyInfo policyInfo);
	
	public void deleteById(String policyId);
	
	public int updateByPrimaryKey(PmPolicyInfo record);
	
	public int countByExample(QueryParams example, List<String> relatedPolicyIds);
	
	public int existPolicyName(String tenantId, String policyName);
}
