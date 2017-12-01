package com.ai.baas.amc.service.atom.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundSerialAtomSV;
import com.ai.opt.sdk.util.DateUtil;

/**
 * 余额相关处理公用类
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class CommonBalanceHandler {
    
    @Autowired
    private IAmcFundSerialAtomSV amcFundSerialAtomSV;
    
    /**
     * 账户校验
     */
    public void validateAccountInfo(String accountId, String tenantId) {
        
    }
    
    /**
     * 幂等性验证
     */
    public String validateDuplication(String tenantId, String systemId, String peerSerialCode) {
        String currentMonth = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMM);
        AmcFundSerial amcFundSerial = amcFundSerialAtomSV.getAmcFundSerial(currentMonth, peerSerialCode, tenantId, systemId);
        if(amcFundSerial != null) {
            return amcFundSerial.getPaySerialCode();
        }
        return null;
    }

}
