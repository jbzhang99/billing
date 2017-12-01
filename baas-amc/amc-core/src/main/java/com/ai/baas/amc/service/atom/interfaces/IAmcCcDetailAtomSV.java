package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;

public interface IAmcCcDetailAtomSV {
    Long searchApportionment(AmcCharge charge);
}
