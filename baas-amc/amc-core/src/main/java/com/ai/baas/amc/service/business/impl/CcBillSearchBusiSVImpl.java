package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.service.atom.interfaces.IAmcCcDetailAtomSV;
import com.ai.baas.amc.service.business.interfaces.ICcBillSearchBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CcBillSearchBusiSVImpl implements ICcBillSearchBusiSV{

    @Autowired
    private IAmcCcDetailAtomSV amcCcDetailAtomSV;

    @Override
    public Long searchApportionmentInfo(AmcCharge charge) {
        return amcCcDetailAtomSV.searchApportionment(charge);
    }
}
