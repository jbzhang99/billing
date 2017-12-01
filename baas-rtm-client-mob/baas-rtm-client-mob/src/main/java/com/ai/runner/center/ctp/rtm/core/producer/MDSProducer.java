package com.ai.runner.center.ctp.rtm.core.producer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class MDSProducer implements IProducer {

	private IMessageSender msgSender = null;
	private int sliver_num = 0;
	private int seed = 0;
	private Lock lock = new ReentrantLock();
	private String srvId;
	private String authAddr;
	private String srvPasswd;
	private String pid;
	private String topic;
	private String sliver;
	
	
	@Override
	public void init() {
		
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

	public String getSrvId() {
		return srvId;
	}

	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}

	public String getAuthAddr() {
		return authAddr;
	}

	public void setAuthAddr(String authAddr) {
		this.authAddr = authAddr;
	}

	public String getSrvPasswd() {
		return srvPasswd;
	}

	public void setSrvPasswd(String srvPasswd) {
		this.srvPasswd = srvPasswd;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSliver() {
		return sliver;
	}

	public void setSliver(String sliver) {
		this.sliver = sliver;
	}
	
}
