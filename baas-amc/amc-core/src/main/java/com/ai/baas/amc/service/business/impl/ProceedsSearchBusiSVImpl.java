package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.service.atom.interfaces.IAmcSettleDetailAtomSV;
import com.ai.baas.amc.service.business.interfaces.IProceedsSearchBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProceedsSearchBusiSVImpl implements IProceedsSearchBusiSV{

    @Autowired
    private IAmcSettleDetailAtomSV amcSettleDetailAtomSV;

    @Override
    public Long searchProceeds(AmcCharge charge) {
        return amcSettleDetailAtomSV.selectProceeds(charge);
    }
}
