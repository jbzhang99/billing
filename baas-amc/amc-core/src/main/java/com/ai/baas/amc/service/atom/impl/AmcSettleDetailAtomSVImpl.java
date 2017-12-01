package com.ai.baas.amc.service.atom.impl;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleDetailCriteria;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcSettleDetail;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcSettleDetailAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class AmcSettleDetailAtomSVImpl implements IAmcSettleDetailAtomSV {

    @Override
    public int addSettleDetail(AmcSettleDetail amcSettleDetail) throws SystemException {
        return MapperFactory.getAmcSettleDetailMapper().insert(amcSettleDetail);
    }

    @Override
    public Long selectProceeds(AmcCharge charge) {
        AmcSettleDetailCriteria condition = new AmcSettleDetailCriteria();
        condition.setBillMonth(charge.getBillMonth());
        AmcSettleDetailCriteria.Criteria criteria = condition.createCriteria();
        criteria.andTenantIdEqualTo(charge.getTenantId());
        criteria.andFeeSubjectIdEqualTo(charge.getSubjectId());
        criteria.andAcctIdEqualTo(Long.parseLong(charge.getAcctId()));
        return MapperFactory.getAmcSettleDetailMapper().selectProceeds(condition);
    }

}
