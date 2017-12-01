package com.ai.baas.bmc.api.packageassemble.impl.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;


@Component
public class BillingConsumerListener {
	
    public BillingConsumerListener() {
        System.out.println("调用调用调用-----------------"); 
    }
    
	@PostConstruct
	public void startListener(){
		String mdsns = "baas-package-topic";
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
