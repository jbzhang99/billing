package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfoCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyInfoAtomSV;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class PmPolicyInfoAtomSVImpl implements PmPolicyInfoAtomSV{

	@Override
	public void add(PmPolicyInfo policyInfo) {
		// TODO Auto-generated method stub
		MapperFactory.getPmPolicyInfoMapper().insert(policyInfo);
	}

	@Override
	public void deleteById(String policyId) {
		// TODO Auto-generated method stub
		MapperFactory.getPmPolicyInfoMapper().deleteByPrimaryKey(policyId);
	}

	@Override
	public int updateByPrimaryKey(PmPolicyInfo record) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyInfoMapper().updateByPrimaryKey(record);
	}

	@Override
	public List<PmPolicyInfo> queryPolicy(QueryParams example, List<String> relatedPolicyIds) {
		// TODO Auto-generated method stub
		PmPolicyInfoCriteria policyInfoCriteria = new PmPolicyInfoCriteria();
		PmPolicyInfoCriteria.Criteria criteria = policyInfoCriteria.createCriteria();
		criteria.andTenantIdEqualTo(example.getTenantId());
		if(!StringUtil.isBlank(example.getPolicyId())){
			criteria.andPolicyIdEqualTo(example.getPolicyId());
		}
		if(!StringUtil.isBlank(example.getPolicyName())){
			criteria.andPolicyNameLike("%"+example.getPolicyName()+"%");
		}
		if(!StringUtil.isBlank(example.getPolicyType())){
			criteria.andPolicyTypeEqualTo(example.getPolicyType());
		}
		if(StringUtils.equals("1", example.getRelated().trim()) && relatedPolicyIds!=null && relatedPolicyIds.size()>0){
			criteria.andPolicyIdNotIn(relatedPolicyIds);
		}
		
		policyInfoCriteria.setLimitStart((example.getPageNo()-1)*example.getPageSize());
		policyInfoCriteria.setLimitEnd(example.getPageSize());
//		policyInfoCriteria.setOrderByClause("");
		return MapperFactory.getPmPolicyInfoMapper().selectByExample(policyInfoCriteria);
	}

	@Override
	public int countByExample(QueryParams example, List<String> relatedPolicyIds) {
		// TODO Auto-generated method stub
		PmPolicyInfoCriteria policyInfoCriteria = new PmPolicyInfoCriteria();
		PmPolicyInfoCriteria.Criteria criteria = policyInfoCriteria.createCriteria();
		criteria.andTenantIdEqualTo(example.getTenantId());
		if(!StringUtil.isBlank(example.getPolicyId())){
			criteria.andPolicyIdEqualTo(example.getPolicyId());
		}
		if(!StringUtil.isBlank(example.getPolicyName())){
			criteria.andPolicyNameLike("%"+example.getPolicyName()+"%");
		}
		if(!StringUtil.isBlank(example.getPolicyType())){
			criteria.andPolicyTypeEqualTo(example.getPolicyType());
		}
		if(StringUtils.equals("1", example.getRelated().trim()) && relatedPolicyIds!=null && relatedPolicyIds.size()>0){
			criteria.andPolicyIdNotIn(relatedPolicyIds);
		}
		
		policyInfoCriteria.setLimitStart((example.getPageNo()-1)*example.getPageSize());
		policyInfoCriteria.setLimitEnd(example.getPageSize());
		return MapperFactory.getPmPolicyInfoMapper().countByExample(policyInfoCriteria);
	}

	@Override
	public PmPolicyInfo selectByPrimaryKey(String policyId) {
		// TODO Auto-generated method stub
		return MapperFactory.getPmPolicyInfoMapper().selectByPrimaryKey(policyId);
	}

    @Override
    public List<PmPolicyInfo> queryPolicyByPolicyId(String policyId, String tenantId) {
        PmPolicyInfoCriteria policyInfoCriteria = new PmPolicyInfoCriteria();
        PmPolicyInfoCriteria.Criteria criteria = policyInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andPolicyIdEqualTo(policyId);
        return MapperFactory.getPmPolicyInfoMapper().selectByExample(policyInfoCriteria);
    }

	@Override
	public int existPolicyName(String tenantId, String policyName) {
		// TODO Auto-generated method stub
		PmPolicyInfoCriteria policyInfoCriteria = new PmPolicyInfoCriteria();
        PmPolicyInfoCriteria.Criteria criteria = policyInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andPolicyNameEqualTo(policyName);
        return MapperFactory.getPmPolicyInfoMapper().countByExample(policyInfoCriteria);
	}


}
