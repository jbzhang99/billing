package com.ai.baas.amc.api.virtualdeduct.impl;

import com.ai.baas.amc.api.virtualdeduct.interfaces.IBalanceQuerySV;
import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.baas.amc.service.business.interfaces.ICashSimulateBusiSV;
import com.ai.baas.amc.service.business.interfaces.IResSimulateBusiSV;
import com.ai.baas.amc.service.business.interfaces.ISimulateBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 模拟销账后的余额查询 <br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Service
@Component
public class BalanceQuerySVImpl implements IBalanceQuerySV{
    private static Logger LOGGER = LoggerFactory.getLogger(BalanceQuerySVImpl.class);
    @Autowired
    public ICashSimulateBusiSV cashSimulate;
    @Autowired
    public IResSimulateBusiSV resSimulate;
    @Override
    public VdRealTimeBalance cancelAccountProcess(BalanceQueryRequest owner) throws BusinessException, SystemException {
        LOGGER.info("模拟销账开始");
        ISimulateBusiSV isi;
        if("cash".equalsIgnoreCase(owner.getBusinessCode())){
            isi=cashSimulate;
        //非现金业务(voice,sm,data),暂不处理
        }else{
            throw new BusinessException("AMC0012001", "不支持的资源类型["+owner.getBusinessCode()+"]");
        }
        VdRealTimeBalance process = null;
        LOGGER.info("初始化ISimulate为" + isi.toString());
        //测试
//        process = demoBalance(owner);
        try {
            isi.init(owner, 100);
            process = isi.process();
            process.setServiceType(owner.getProductType());
            process.setReturnCode("MMP-000000");
        } catch (BusinessException e){
            throw new BusinessException("AMC0012001", e);
        }

        LOGGER.info("模拟销账结束");
        return process;
    }

//    /**
//     * 用于模拟返回示例
//     *
//     * @param owner
//     * @return
//     */
//    private VdRealTimeBalance demoBalance(BalanceQueryRequest owner){
//        VdRealTimeBalance realTimeBalance = new VdRealTimeBalance();
//        realTimeBalance.setOwner(owner);
//        realTimeBalance.setServiceType(owner.getProductType());
//        realTimeBalance.setReturnCode("MMP-000000");
//        //当前总可销账余额
//        realTimeBalance.setBalance(BigDecimal.ZERO);
//        //当前信用度
//        realTimeBalance.setCreditLine(BigDecimal.ZERO);
//        //抵扣后预存
//        realTimeBalance.setRealBalance(BigDecimal.ZERO);
//        //当前实时欠费
//        realTimeBalance.setRealBill(BigDecimal.ZERO);
//        //
//        realTimeBalance.setUnIntoBill(BigDecimal.ZERO);
//        //当前历史欠费
//        realTimeBalance.setUnSettleBill(BigDecimal.ZERO);
//        //最早欠费月份
//        realTimeBalance.setFstUnsettLemon("201603");
//        //当前账期
//        realTimeBalance.setAcctMonth("201604");
//        //扩展信息
//        realTimeBalance.setExpandInfo("{}");
//        return realTimeBalance;
//    }
}
