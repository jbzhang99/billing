package com.ai.baas.bmc.topology.util;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;

/**
 * MDS代理类
 * @author majun
 *
 */
public class MDSProxy {
	
	public static IMessageSender getMessageSender(String broker_list, String topic){
		if(StringUtils.isBlank(topic) || StringUtils.isBlank(broker_list)){
			return null;
		}
		Properties properties = new Properties();
        properties.setProperty("metadata.broker.list",broker_list);
        properties.setProperty("serializer.class","kafka.serializer.DefaultEncoder");
        properties.setProperty("key.serializer.class","kafka.serializer.StringEncoder");
        properties.setProperty("partitioner.class","com.ai.paas.ipaas.mds.impl.sender.ModPartitioner");
        properties.setProperty("request.required.acks","1");
        properties.setProperty("queue.buffering.max.messages","1048576");
        properties.setProperty("producer.type","sync");
        properties.setProperty("message.send.max.retries","3");
        properties.setProperty("compression.codec","none");
        properties.setProperty("request.timeout.ms","20000");
        properties.setProperty("batch.num.messages","64000");
        properties.setProperty("send.buffer.bytes","67108864");
        properties.setProperty("maxProducer","5");
//		return MsgSenderFactory.getClient(properties, topic);
        return null;
}
	
}
