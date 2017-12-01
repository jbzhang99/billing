package com.ai.baas.amc.service.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.oweinfo.params.OweInfoCreateRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.business.interfaces.IOweInfoBusiSV;
import com.ai.opt.sdk.util.DateUtil;

@Component
@Transactional
public class OweInfoBusiSVImpl implements IOweInfoBusiSV {

    @Override
    public void createOweInfo(OweInfoCreateRequest request) {
        Timestamp sysdate = DateUtil.getSysDate();

        AmcOweInfo amcOweInfo = new AmcOweInfo();
        amcOweInfo.setTenantId(request.getTenantId());
        amcOweInfo.setAcctId(request.getAcctId());
        amcOweInfo.setCreateTime(sysdate);
        amcOweInfo.setCustId(request.getCustId());
        amcOweInfo.setCustName(request.getCustName());
        amcOweInfo.setMonth(request.getMonth());
        amcOweInfo.setBalance(BigDecimal.ZERO);
        amcOweInfo.setConfirmTime(sysdate);
        MapperFactory.getAmcOweInfoMapper().insertSelective(amcOweInfo);
    }

}
