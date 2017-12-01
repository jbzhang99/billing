package com.ai.baas.amc.mds;


import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;
import com.ai.baas.amc.service.business.interfaces.IAmcFailureBillBusiSV;
import com.ai.baas.amc.service.business.interfaces.IWriteOffBusiSV;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.alibaba.fastjson.JSONObject;

public class WriteOffProcessorImpl implements IMessageProcessor {
    private static final Logger LOG = LogManager.getLogger(WriteOffProcessorImpl.class);
    
    private IWriteOffBusiSV writeOffBusiSV;
    
    private IAmcFailureBillBusiSV amcFailureBillBusiSV;

    
    public WriteOffProcessorImpl(IWriteOffBusiSV writeOffBusiSV,IAmcFailureBillBusiSV amcFailureBillBusiSV) {
        this.writeOffBusiSV = writeOffBusiSV;
        this.amcFailureBillBusiSV = amcFailureBillBusiSV;
    }
    @Override
    public void process(MessageAndMetadata message) throws Exception {
        LOG.info("销账处理listener开始处理.........");

        if (null == message){
            return;}
        String content = new String(message.getMessage(), "UTF-8");
        
        LOG.info("销账处理listener接收到MDS消息：["+content+"]");
        String acctId="";
        String tenantId="";
        try {
            JSONObject jsonRoot = JSONObject.parseObject(content);
            acctId = jsonRoot.getString(AmcConstants.APMessage.ACCT_ID);
            tenantId = jsonRoot.getString(AmcConstants.APMessage.TENANT_ID);
            writeOffBusiSV.writeCore(acctId, tenantId);
        } catch (Exception e) {
            AmcFailureBillWithBLOBs failur = new AmcFailureBillWithBLOBs();
            failur.setTenantId(tenantId);
            failur.setArrivalTime(new Timestamp(System.currentTimeMillis()));
            failur.setFailCode("write_off");
            failur.setFailDate(new Timestamp(System.currentTimeMillis()));
            failur.setFailPacket(content);

            ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
//            e.printStackTrace(new java.io.PrintWriter(buf, true));
            LOG.error("销账错误",e);
            String  expMessage = buf.toString();
            buf.close();
            failur.setFailReason(expMessage);
            failur.setFailStep("销账");
            amcFailureBillBusiSV.addFailureBill(failur);
            LOG.error("销账错误，报文：["+content+"]",e);
            LOG.error("销账错误:"+e.getMessage(),e);
        }
    }

}
