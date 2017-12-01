package com.ai.baas.amc.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcSettleLog;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcSettleLogAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class AmcSettleLogAtomSVImpl implements IAmcSettleLogAtomSV {

    @Override
    public int addSettleLog(AmcSettleLog amcSettleLog) throws SystemException {
        return MapperFactory.getAmcSettleLogMapper().insert(amcSettleLog);
    }

   
}
