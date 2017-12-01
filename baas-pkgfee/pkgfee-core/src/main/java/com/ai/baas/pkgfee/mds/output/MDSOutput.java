package com.ai.baas.pkgfee.mds.output;

import java.util.Random;

import com.ai.paas.ipaas.mds.IMessageSender;

public class MDSOutput implements IOutput {

	@Override
	public void send(String josnMessage) {
		System.out.println("包费计算输出消息:"+josnMessage);
		IMessageSender msgSender = MDSClient.getSender();
		if (msgSender != null) {
			msgSender.send(josnMessage, new Random(1000).nextLong());
		}
	}

	
}
