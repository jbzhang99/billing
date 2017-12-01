package com.ai.baas.amc.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;
import com.ai.baas.amc.service.atom.interfaces.IAmcFailureBillAtomSV;
import com.ai.baas.amc.service.business.interfaces.IAmcFailureBillBusiSV;
@Service
@Transactional
public class AmcFailureBillBusiSVImpl implements IAmcFailureBillBusiSV {
    @Autowired
    IAmcFailureBillAtomSV amcFailureBillAtomSV;
    @Override
    public void addFailureBill(AmcFailureBillWithBLOBs amcFailureBill) {
        amcFailureBillAtomSV.addFailureBill(amcFailureBill);
    }

}
