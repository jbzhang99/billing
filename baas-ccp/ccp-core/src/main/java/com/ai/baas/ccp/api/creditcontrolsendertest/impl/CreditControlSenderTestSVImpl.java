package com.ai.baas.ccp.api.creditcontrolsendertest.impl;

import java.util.Random;

import com.ai.baas.ccp.api.creditcontrolsendertest.interfaces.ICreditControlSenderTestSV;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class CreditControlSenderTestSVImpl implements ICreditControlSenderTestSV {

    @Override
    public void sendMessage(String message) {
        String mdsns = "BaaS_OMC_MDS";
        IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
        // send方法的第二个参数为随机数，依据该随机数均匀往各个片区发送消息
        msgSender.send(message, new Random(1000).nextLong());
    }

}
