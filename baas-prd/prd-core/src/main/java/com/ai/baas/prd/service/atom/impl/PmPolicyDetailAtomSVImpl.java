package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetail;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetailCriteria;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfoCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyDetailAtomSV;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class PmPolicyDetailAtomSVImpl implements PmPolicyDetailAtomSV{

	@Override
	public void add(PmPolicyDetail policyDetail) {
		// TODO Auto-generated method stub
		
		MapperFactory.getPmPolicyDetailMapper().insert(policyDetail);
	}

	@Override
	public int deleteByExample(PmPolicyDetailCriteria example) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyDetailMapper().deleteByExample(example);
	}

	@Override
	public List<PmPolicyDetail> selectByExample(PmPolicyDetailCriteria example) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyDetailMapper().selectByExample(example);
	}
	
	public List<PmPolicyDetail> selectByPolicyID( String pricePolicy, String tenantId) {
		PmPolicyDetailCriteria pmPolicyDetailCriteria = new PmPolicyDetailCriteria();
        PmPolicyDetailCriteria.Criteria criteria = pmPolicyDetailCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andPolicyIdEqualTo(pricePolicy);

        return MapperFactory.getPmPolicyDetailMapper().selectByExample(pmPolicyDetailCriteria);
	}

	@Override
	public List<PmPolicyDetail> selectByDetailID( String detailID, String tenantId) {
		PmPolicyDetailCriteria pmPolicyDetailCriteria = new PmPolicyDetailCriteria();
        PmPolicyDetailCriteria.Criteria criteria = pmPolicyDetailCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andDetailIdEqualTo(detailID);

        return MapperFactory.getPmPolicyDetailMapper().selectByExample(pmPolicyDetailCriteria);
	}
	
}
