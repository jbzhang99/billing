package com.ai.baas.smc.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.smc.dao.mapper.bo.StlPolicyCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.baas.smc.service.atom.interfaces.IPolicyManageAtomSV;

@Component
public class PolicyManageAtomSVImpl implements IPolicyManageAtomSV {

    @Override
    public boolean isExistPolicyCode(String tenantId, String policyCode) {
        StlPolicyCriteria criteria = new StlPolicyCriteria();
        criteria.createCriteria().andTenantIdEqualTo(tenantId).andPolicyCodeEqualTo(policyCode);
        int count = MapperFactory.getStlPolicyMapper().countByExample(criteria);
        return count > 0;
    }

}
