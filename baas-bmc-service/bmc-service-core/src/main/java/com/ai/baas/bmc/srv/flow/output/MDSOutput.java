package com.ai.baas.bmc.srv.flow.output;

import java.util.Random;

import com.ai.baas.bmc.srv.entity.BillingDetailRecord;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.fastjson.JSON;

public class MDSOutput implements IOutput {
	
	@Override
	public void send(BillingDetailRecord detailRecord) {
		//String message = JSON.toJSONString(detailRecord.getData());
		String message = JSON.toJSONString(detailRecord.getMsgData());
		//System.out.println("计费输出消息:"+message);
		IMessageSender msgSender = MDSClient.getSender();
		if (msgSender != null) {
			msgSender.send(message, new Random(1000).nextLong());
		}
		
	}
	
}
