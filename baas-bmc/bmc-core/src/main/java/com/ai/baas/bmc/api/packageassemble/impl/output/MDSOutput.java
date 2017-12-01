package com.ai.baas.bmc.api.packageassemble.impl.output;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.fastjson.JSONObject;

public class MDSOutput {
    private static Logger logger = LoggerFactory.getLogger(MDSOutput.class);
    public void sendMsg(JSONObject output ) throws Exception{
        String mdsns = "baas_bmc_service_out_queue";//baas_bmc_service_out_queue
        logger.info("mdsn: "+mdsns);
        IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
        msgSender.send(output.toJSONString(), 0);
        
    }

}
