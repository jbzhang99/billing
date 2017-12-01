package com.ai.baas.amc.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.paymentquery.param.PaymentLog;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundSerialAtomSV;
import com.ai.baas.amc.service.business.interfaces.IPaymentQueryBusiSV;
import com.ai.baas.amc.util.CustInfoUtil;
import com.ai.baas.amc.vo.CustInfo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * 缴费查询具体实现类.<br>
 *
 * Date: 2016年4月17日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class PaymentQueryBusiSVImpl implements IPaymentQueryBusiSV {
        
    @Autowired
    private IAmcFundSerialAtomSV amcFundSerialAtomSV;

    @Override
    public PageInfo<PaymentLog> queryPaymentLog(PaymentLogQueryRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        PageInfo<AmcFundSerial> fundSerialPageInfo = amcFundSerialAtomSV.queryAmcFundSerialList(request);
        PageInfo<PaymentLog> pageInfo = new PageInfo<PaymentLog>();
        List<PaymentLog> paymentLogList = new ArrayList<PaymentLog>();
        if(!CollectionUtil.isEmpty(fundSerialPageInfo.getResult())) {
            for(AmcFundSerial fundSerial : fundSerialPageInfo.getResult()) {
                PaymentLog paymentLog = this.assemablePaymentLog(tenantId, fundSerial);
                paymentLogList.add(paymentLog);
            }
            
        }
        
        pageInfo.setCount(fundSerialPageInfo.getCount());
        pageInfo.setPageNo(fundSerialPageInfo.getPageNo());
        pageInfo.setPageSize(fundSerialPageInfo.getPageSize());
        pageInfo.setResult(paymentLogList);
        return pageInfo;
    }
    
    /**
     * 封装缴费流水记录实体
     * @param tenantId
     * @param fundSerial
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private PaymentLog assemablePaymentLog(String tenantId, AmcFundSerial fundSerial) {
        String custId = fundSerial.getCustId1();
        CustInfo custInfo = CustInfoUtil.getCustInfo(tenantId, custId);
        if(custInfo == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取不到对应的客户信息[" + custId + "]");
        }
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setTenantId(tenantId);
        paymentLog.setPaySerialCode(fundSerial.getPaySerialCode());
        paymentLog.setCustId(custId);
        paymentLog.setCustName(custInfo.getCustName());
        paymentLog.setCustGrade(custInfo.getCustGrade());
        paymentLog.setTotalAmount(fundSerial.getTotalAmount());
        paymentLog.setOptType(AmcConstants.FunFundSerial.OptType.DEPOSIT);
        paymentLog.setPeerSerialCode(fundSerial.getPeerSerialCode());
        paymentLog.setPayTime(fundSerial.getCreateTime());
        return paymentLog;
    }
}
