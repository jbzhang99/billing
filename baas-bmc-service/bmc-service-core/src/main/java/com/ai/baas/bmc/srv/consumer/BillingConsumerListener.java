package com.ai.baas.bmc.srv.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;


/**
 * 计费消费者监听器
 * @author majun
 *
 */
@Component
public class BillingConsumerListener {
	
	@PostConstruct
	public void startListener(){
		String mdsns = "baas_bmc_service_in_queue";
		IMsgProcessorHandler msgProcessorHandler = new IMsgProcessorHandler() {
			
			@Override
			public IMessageProcessor[] createInstances(int paramInt) {
				List<IMessageProcessor> processors = new ArrayList<>();
				IMessageProcessor processor = null;
				for (int i = 0; i < paramInt; i++) {
					processor = new MessageProcessorImpl();
					processors.add(processor);
				}
				return processors.toArray(new IMessageProcessor[processors.size()]);
				
			}
		};
		IMessageConsumer msgConsumer= MDSClientFactory.getConsumerClient(mdsns, msgProcessorHandler,"mds-consumer-"+mdsns);
		msgConsumer.start();
		
	}
	
	
}
