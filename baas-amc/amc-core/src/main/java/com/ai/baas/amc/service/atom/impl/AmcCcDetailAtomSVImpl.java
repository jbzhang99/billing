package com.ai.baas.amc.service.atom.impl;

import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcCcDetailAtomSV;
import org.springframework.stereotype.Component;

@Component
public class AmcCcDetailAtomSVImpl implements IAmcCcDetailAtomSV{
    private static final String IS_APPORTION = "Y";

    @Override
    public Long searchApportionment(AmcCharge charge) {
        AmcCcDetailCriteria condition = new AmcCcDetailCriteria();
        condition.setBillMonth(charge.getBillMonth());
        AmcCcDetailCriteria.Criteria criteria = condition.createCriteria();
        criteria.andAcctIdEqualTo(charge.getAcctId());
        criteria.andTenantIdEqualTo(charge.getTenantId());
        criteria.andSubjectIdEqualTo(charge.getSubjectId());
        criteria.andIsApportionEqualTo(IS_APPORTION);
        return MapperFactory.getAmcCcDetailMapper().selectApportionmentByAcctId(condition);
    }
}
