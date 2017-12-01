package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactor;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactorCriteria;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariable;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariableCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyVariableAtomSV;

@Component
public class PmPolicyVariableAtomSVImpl implements PmPolicyVariableAtomSV{

	@Override
	public void add(PmPolicyVariable variable) {
		// TODO Auto-generated method stub
		MapperFactory.getPmPolicyVariableMapper().insert(variable);
	}

	@Override
	public int deleteByExample(PmPolicyVariableCriteria example) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyVariableMapper().deleteByExample(example);
	}

	@Override
	public List<PmPolicyVariable> selectByExample(PmPolicyVariableCriteria example) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyVariableMapper().selectByExample(example);
	}

    @Override
    public List<PmPolicyVariable> queryPolicyByPolicy(String pricePolicy, String tenantId, String varCode) {
        PmPolicyVariableCriteria variInfoCriteria = new PmPolicyVariableCriteria();
        PmPolicyVariableCriteria.Criteria criteria = variInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andVarCodeEqualTo(varCode);
        criteria.andPolicyIdEqualTo(pricePolicy);
        return MapperFactory.getPmPolicyVariableMapper().selectByExample(variInfoCriteria);
    }
    
    @Override
    public List<PmPolicyVariable> queryVariablesByPolicyId(String pricePolicy, String tenantId){
    	PmPolicyVariableCriteria variInfoCriteria = new PmPolicyVariableCriteria();
        PmPolicyVariableCriteria.Criteria criteria = variInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andPolicyIdEqualTo(pricePolicy);
        return MapperFactory.getPmPolicyVariableMapper().selectByExample(variInfoCriteria);
    }
    
    
}
