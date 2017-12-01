package com.ai.baas.amc.service.business.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.baas.amc.api.deposit.param.TransSummary;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.service.atom.impl.CommonBalanceHandler;
import com.ai.baas.amc.service.atom.interfaces.IDepositAtomSV;
import com.ai.baas.amc.service.business.interfaces.IDepositBusiSV;
import com.ai.baas.amc.vo.DepositVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 存款服务业务实现类
 *
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class DepositBusiSVImpl extends CommonBalanceHandler implements IDepositBusiSV {
    
    private static final Logger LOG = LogManager.getLogger(DepositBusiSVImpl.class);
    
    @Autowired
    private IDepositAtomSV depositAtomSV;

    @Override
    public String depositFund(DepositRequest request) throws BusinessException {
        /* 参数转化 */
        DepositVo depositVo = new DepositVo();
        BeanUtils.copyProperties(depositVo, request);
        for (TransSummary transSummary : request.getTransSummary()) {
            BeanUtils.copyProperties(depositVo.createTransSummary(), transSummary);
        }
        
        /* 1.合法性校验 */
        /* 1.1 账户校验 */
        this.validateAccountInfo(depositVo.getAccountId(), depositVo.getTenantId());
        /* 1.2 幂等性验证 */
        String paySerialCode = this.validateDuplication(depositVo.getTenantId(),
                depositVo.getSystemId(), depositVo.getBusiSerialNo());
        if(!StringUtil.isBlank(paySerialCode)) {
            LOG.info("存款成功，返回存款交易流水号: " + paySerialCode);
            return paySerialCode;
        }
        /* 1.3 资金科目校验 */
        // 现金存款，资金类型只能是现金或赠款
        depositVo.addFundType(AmcConstants.FunSubject.FundType.CASH);
        depositVo.addFundType(AmcConstants.FunSubject.FundType.GRANT);
        Map<Long, SubjectFundVo> subjectFundVoMap = depositAtomSV.validateSubject(depositVo);
        
        /* 2.账本确定，更新账本 */
        depositAtomSV.locateFundBook(depositVo, subjectFundVoMap);
        
        /* 3.记录交易流水 */
        paySerialCode = depositAtomSV.savePaySerial(depositVo); 
        LOG.info("存款成功，返回交易流水号：" + paySerialCode);
        return paySerialCode;
    }    
}
