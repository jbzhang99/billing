package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetail;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetailCriteria;

public interface PmPolicyDetailAtomSV  {

	public void add(PmPolicyDetail policyDetail);
	
	public int deleteByExample(PmPolicyDetailCriteria example);
	
	public List<PmPolicyDetail> selectByExample(PmPolicyDetailCriteria example);

    public List<PmPolicyDetail> selectByPolicyID(String pricePolicy, String tenantId);
    
    public List<PmPolicyDetail> selectByDetailID( String detailID, String tenantId);
}
