package com.ai.baas.smc.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.smc.constants.SmcConstants;
import com.ai.baas.smc.dao.mapper.bo.StlBillStyleCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.baas.smc.service.atom.interfaces.IStlBillStyleAtomSV;

@Component
public class StlBillStyleAtomSVImpl implements IStlBillStyleAtomSV {

    @Override
    public boolean isValidBillStyleSn(String tenantId, String billStyleSn) {
        StlBillStyleCriteria criteria = new StlBillStyleCriteria();
        criteria.createCriteria().andTenantIdEqualTo(tenantId).andBillStyleSnEqualTo(billStyleSn)
                .andStateEqualTo(SmcConstants.StlBillStyle.State.NORMAL);
        int count = MapperFactory.getStlBillStyleMapper().countByExample(criteria);
        if (count > 0) {
            return true;
        }
        return false;
    }

}
