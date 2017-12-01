package com.ai.baas.bmc.topology.billing.output;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.util.MDSProxy;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public abstract class AbstractMDSOutput implements IOutput {
	public static final String BROKER_LIST = "billing.output.mds.broker.list";
	private IMessageSender sender;
	protected int partition = 1;
	protected String topic;
	protected String broker;
	
//	public void init(String topic, String broker, String part){
//		if (StringUtils.isNotBlank(topic) && StringUtils.isNotBlank(broker)) {
//			sender = MDSProxy.getMessageSender(broker, topic);
//		}
//		if(StringUtils.isNotBlank(part)){
//			partition = Integer.parseInt(part);
//		}
//	}
	
     public void init(String topic, String broker, String part , String authAddr , String pId, String password , String srvId){
        if (StringUtils.isNotBlank(topic) && StringUtils.isNotBlank(broker)) {
            AuthDescriptor ad = new AuthDescriptor(authAddr,pId,password,srvId);
            sender = MsgSenderFactory.getClient(ad, topic);
        }
        if(StringUtils.isNotBlank(part)){
            partition = Integer.parseInt(part);
        }
     }
	
	@Override
	public void send(BillingDetailRecord detailRecord) {
		//assembleData(detailRecord);
		System.out.println("the sender part is "+detailRecord.getRecordFmtKey().hashCode()%partition);
		System.out.println("the value is "+detailRecord.getRecordFmtKey().hashCode()%partition);
		System.out.println("the partion is "+partition);
		sender.send(assembleData(detailRecord), detailRecord.getRecordFmtKey().hashCode()%partition%partition);
	}
	
//	public void send(String deductData){
//		sender.send(deductData, detailRecord.getRecordFmtKey().hashCode()%partition);
//	}

	public abstract String assembleData(BillingDetailRecord detailRecord);
	
	

}
