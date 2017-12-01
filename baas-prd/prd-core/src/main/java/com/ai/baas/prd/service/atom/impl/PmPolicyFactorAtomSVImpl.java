package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactor;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactorCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyFactorAtomSV;

@Component
public class PmPolicyFactorAtomSVImpl implements PmPolicyFactorAtomSV{

	@Override
	public void add(PmPolicyFactor factor) {
		// TODO Auto-generated method stub
		MapperFactory.getPmPolicyFactorMapper().insert(factor);
	}

	@Override
	public int deleteByExample(PmPolicyFactorCriteria example) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyFactorMapper().deleteByExample(example);
	}

	@Override
	public List<PmPolicyFactor> selectByExample(PmPolicyFactorCriteria example) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyFactorMapper().selectByExample(example);
	}
	
    @Override
    public List<PmPolicyFactor> queryPolicyByDetailId(String detailId, String tenantId) {
        PmPolicyFactorCriteria detailInfoCriteria = new PmPolicyFactorCriteria();
        PmPolicyFactorCriteria.Criteria criteria = detailInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andDetailIdEqualTo(detailId);
        return MapperFactory.getPmPolicyFactorMapper().selectByExample(detailInfoCriteria);
    }
    

	@Override
	public PmPolicyFactor getFactorById(String id) {
		return MapperFactory.getPmPolicyFactorMapper().selectByPrimaryKey(Long.parseLong(id));
	}
	
	@Override
	public List<PmPolicyFactor> queryPolicyByDetailIds(List<String> detailIds, String tenantId){
		PmPolicyFactorCriteria detailInfoCriteria = new PmPolicyFactorCriteria();
        PmPolicyFactorCriteria.Criteria criteria = detailInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andDetailIdIn(detailIds);
        return MapperFactory.getPmPolicyFactorMapper().selectByExample(detailInfoCriteria);
	}
}
