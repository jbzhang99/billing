package com.ai.baas.amc.mds;


import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;
import com.ai.baas.amc.service.business.interfaces.IAccountPreferentialBusiSV;
import com.ai.baas.amc.service.business.interfaces.IAmcFailureBillBusiSV;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.alibaba.fastjson.JSONObject;

public class AccountPreferentialProcessorImpl implements IMessageProcessor {
    private static final Logger LOG = LogManager.getLogger(AccountPreferentialProcessorImpl.class);
    
    private IAccountPreferentialBusiSV accountPreferentialBusiSV;
    private IAmcFailureBillBusiSV amcFailureBillBusiSV;
    
    public AccountPreferentialProcessorImpl(IAccountPreferentialBusiSV accountPreferentialBusiSV,IAmcFailureBillBusiSV amcFailureBillBusiSV) {
        this.accountPreferentialBusiSV = accountPreferentialBusiSV;
        this.amcFailureBillBusiSV = amcFailureBillBusiSV;
    }
    @Override
    public void process(MessageAndMetadata message) throws Exception {
        LOG.info("账务处理listener开始处理.........");
        if (null == message){
            LOG.info("账务处理listener接收到MDS消息为空");
            return;
        }
        String content = new String(message.getMessage(), "UTF-8");
        String tenantId ="";
        try {
            
            LOG.info("账务处理listener接收到MDS消息：["+content+"]");
            /*解析报文*/
            JSONObject jsonRoot = JSONObject.parseObject(content);
            accountPreferentialBusiSV.accountPreferentialApportion(jsonRoot);
            /*根据分摊拆分金额*/
            
        } catch (Exception e) {   
            AmcFailureBillWithBLOBs failur = new AmcFailureBillWithBLOBs();
            failur.setTenantId(tenantId);
            failur.setArrivalTime(new Timestamp(System.currentTimeMillis()));
            failur.setFailCode("account_preferential");
            failur.setFailDate(new Timestamp(System.currentTimeMillis()));
            failur.setFailPacket(content);
            ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
            e.printStackTrace(new java.io.PrintWriter(buf, true));
            String  expMessage = buf.toString();
            buf.close();
            failur.setFailReason(expMessage);
            failur.setFailStep("账务优惠");
            amcFailureBillBusiSV.addFailureBill(failur);
            LOG.error("账务优惠错误，报文：["+content+"]",e);
            LOG.error("账务优惠错误:"+e.getMessage(),e);
        }
    }
    
//    /**
//     * 传入的不是acctId，需要查询acctId
//     * @param param
//     * @return
//     * @author LiangMeng
//     */
//    private String queryAcctId(String param){
//        String acctId = param;
//        return acctId;
//    }
//    /**
//     * 查询custId
//     * @param param
//     * @return
//     * @author LiangMeng
//     */
//    private String queryCustId(String param){
//        String acctId = param;
//        return acctId;
//    }

}
