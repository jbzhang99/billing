package com.ai.runner.center.ctp.rtm.core.producer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class MDSProducer implements IProducer {

	private IMessageSender msgSender = null;
	private int sliver_num = 0;
	private int seed = 0;
	private Lock lock = new ReentrantLock();
	
	
	@Override
	public void init() {
		String srvId = (String)PropertiesUtil.getValue("ctp.rtm.mds.service.id");
		String authAddr = (String)PropertiesUtil.getValue("ctp.rtm.mds.auth.addr");
		String srvPasswd = (String)PropertiesUtil.getValue("ctp.rtm.mds.service.pwd");
		String pid = (String)PropertiesUtil.getValue("ctp.rtm.mds.pid");
		String topic = (String)PropertiesUtil.getValue("ctp.rtm.message.topic");
		String sliver = (String)PropertiesUtil.getValue("ctp.rtm.mds.sliver.num");
		sliver_num = Integer.parseInt(sliver);
		
		AuthDescriptor ad = new AuthDescriptor(authAddr,pid,srvPasswd,srvId);
		msgSender = MsgSenderFactory.getClient(ad, topic);
	}

	@Override
	public void sendMessage(String message) {
		lock.lock();
		msgSender.send(message, getSliverNum());
		seed++;
		lock.unlock();
	}
	
	@Override
	public void close(){
	}

	private int getSliverNum(){
		if(seed >= Integer.MAX_VALUE){
			seed = 0;
		}
		return seed%sliver_num;
	}
	
}
