package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.baas.amc.service.business.interfaces.IResSimulateBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 资源模拟流程
 */
@Service
public class ResSimulateBusiSVImpl implements IResSimulateBusiSV {
    private static Logger LOGGER = LoggerFactory.getLogger(ResSimulateBusiSVImpl.class);
    private BalanceQueryRequest owner;
    private ResAccountFeeFundBusiSVImpl resAccountFeeFund;
    @Override
    public Boolean init(BalanceQueryRequest owner, double extbalance) throws BusinessException,SystemException {
        this.owner = owner;
//        resAccountFeeFund=new ResAccountFeeFund(owner,extbalance);
        return true;
    }

    @Override
    public VdRealTimeBalance process() throws BusinessException,SystemException {
        return null;
    }
}
