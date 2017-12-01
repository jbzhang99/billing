package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.baas.amc.service.business.interfaces.IAccountFeeFundBusiSV;

import java.math.BigDecimal;

/**
 * Created by jackieliu on 16/3/30.
 */
public class ResAccountFeeFundBusiSVImpl implements IAccountFeeFundBusiSV {
    //抵扣后情况
    private VdRealTimeBalance realTimeBalanceAfterDecuct;
    //资源类型信息
//    private ResAmount amounts;
    private BalanceQueryRequest owner;
    @SuppressWarnings("unused")
    private BigDecimal extBalance;
    @Override
    public VdRealTimeBalance process() {
        return null;
    }
}
