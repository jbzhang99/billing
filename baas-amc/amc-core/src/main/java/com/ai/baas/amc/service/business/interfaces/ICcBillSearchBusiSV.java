package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;

public interface ICcBillSearchBusiSV {
    Long searchApportionmentInfo(AmcCharge charge);
}
