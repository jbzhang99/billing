package com.ai.baas.amc.mds;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.service.business.interfaces.IAccountPreferentialBusiSV;
import com.ai.baas.amc.service.business.interfaces.IAmcFailureBillBusiSV;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
/**
 * 账务优惠MDS监听类
 * Date: 2016年7月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
@Service
@Transactional
public class AccountPreferentialMDSListener   {
    @Autowired
    IAccountPreferentialBusiSV accountPreferentialBusiSV;
    @Autowired
    IAmcFailureBillBusiSV amcFailureBillBusiSV;
	@PostConstruct
    public void RouteChargeMdsProcess() {
        IMsgProcessorHandler msgProcessorHandler = new IMsgProcessorHandler() {
            @Override
            public IMessageProcessor[] createInstances(int paramInt) {
                List<IMessageProcessor> processors = new ArrayList<>();
                IMessageProcessor processor = null;
                for (int i = 0; i < paramInt; i++) {
                    processor = new AccountPreferentialProcessorImpl(accountPreferentialBusiSV,amcFailureBillBusiSV);
                    processors.add(processor);
                }
                return processors.toArray(new IMessageProcessor[processors.size()]);
            }
        };
        IMessageConsumer msgConsumer = MDSClientFactory.getConsumerClient(
                AmcConstants.MDSTopic.AP_TOPIC, msgProcessorHandler);
        msgConsumer.start();
    }
}
