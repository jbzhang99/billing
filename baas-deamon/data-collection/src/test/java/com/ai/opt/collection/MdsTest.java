package com.ai.opt.collection;

import java.util.Random;

import org.junit.Test;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;

public class MdsTest {
	public final static String HEAD_SPLIT = new String(new char[] { (char) 1 }); // 消息头各个字段分割
	public final static String MESS_SPLIT = new String(new char[] { (char) 2 }); // 消息的分割
	public final static String RECORD_SPLIT = new String(new char[] { (char) 3 }); // 消息和消息头的分割

	@Test
	public void sendMsgTest() {
		String mdsns = "ns-baas-data-collection";//
		IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
		StringBuilder sb = new StringBuilder();
		// msgSender.getParititions() 方法废弃，以后不再调用
		for (int i = 0; i < 2; i++) {
			
			sb.append("天润消息" + i);
			if (i != 2) {
					sb.append(MESS_SPLIT);
			}
			// send方法的第二个参数为随机数，依据该随机数均匀往各个片区发送消息
			//msgSender.send("[test-baas-bmc-topic-msg:" + i + "]This is a test message……", new Random(1000).nextLong());
			// System.out.println("sender---[test-baas-bmc-topic-msg:"+i+"]This
			// is a test message……");

		}
		msgSender.send(sb.toString(), new Random(1000).nextLong());
			
		
	}
	/*
	 * @Test public void recvMsgTest(){ String mdsns = "baas-bmc-topic";//
	 * IMsgProcessorHandler msgProcessorHandler=new IMsgProcessorHandler() {
	 * 
	 * @Override public IMessageProcessor[] createInstances(int paramInt) { //
	 * TODO Auto-generated method stub
	 * 
	 * List<IMessageProcessor> processors = new ArrayList<>(); IMessageProcessor
	 * processor = null; for (int i = 0; i < paramInt; i++) { processor = new
	 * MessageProcessorImpl(); processors.add(processor); } return
	 * processors.toArray(new IMessageProcessor[processors.size()]);
	 * 
	 * } }; // IMessageConsumer msgConsumer=
	 * MDSClientFactory.getConsumerClient(mdsns,
	 * msgProcessorHandler,"mds-consumer-mytopic1"); IMessageConsumer
	 * msgConsumer= MDSClientFactory.getConsumerClient(mdsns,
	 * msgProcessorHandler,"mds-consumer-mytopic1111"); msgConsumer.start();
	 * synchronized (MdsTest.class) { while (true) { try { MdsTest.class.wait();
	 * 
	 * } catch (Exception e) { System.out.println("MDS 消费错误，具体信息为：" +
	 * e.getMessage()); } } }
	 * 
	 * }//end of method
	 */
}
