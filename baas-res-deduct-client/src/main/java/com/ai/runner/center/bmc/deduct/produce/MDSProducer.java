package com.ai.runner.center.bmc.deduct.produce;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.runner.center.bmc.deduct.constants.ProducerConst;
import com.ai.runner.center.bmc.deduct.util.PropertiesUtil;

public class MDSProducer implements IProducer {

	private static final Logger LOG = LoggerFactory
			.getLogger(MDSProducer.class);

	private IMessageSender msgSender = null;
	private int sliver_num = 0;
	private int seed = 0;
	private Lock lock = new ReentrantLock();

	@Override
	public void init() {

		String srvId = PropertiesUtil.getProValue(ProducerConst.SRV_ID);
		String authAddr = PropertiesUtil.getProValue(ProducerConst.AUTH_ADDR);
		String srvPasswd = PropertiesUtil
				.getProValue(ProducerConst.SERVICE_PASSWORD);
		String pid = PropertiesUtil.getProValue(ProducerConst.AUTH_PID);
		String topic = PropertiesUtil.getProValue(ProducerConst.MSG_TOPIC);

		String sliver = PropertiesUtil.getProValue(ProducerConst.SLIVER);
		try {
			sliver_num = Integer.parseInt(sliver);
		} catch (Exception e) {
			LOG.warn("parseInt sliver in conf error！", e);
			e.printStackTrace();
		} finally {
			sliver_num = 1;
		}
		AuthDescriptor ad = new AuthDescriptor(authAddr, pid, srvPasswd, srvId);

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
	public void close() {
	}

	private int getSliverNum() {
		if (seed >= Integer.MAX_VALUE) {
			seed = 0;
		}
		return seed % sliver_num;
	}

}
