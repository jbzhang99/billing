package com.ai.baas.job.service.busi;

import java.util.Map;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;

public class MDSOutput {

    public void sendMsg(String productId ){
        String mdsns = "baas-package-topic";//
        IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
        msgSender.send(productId.toString(), 0);
        
    }

}
