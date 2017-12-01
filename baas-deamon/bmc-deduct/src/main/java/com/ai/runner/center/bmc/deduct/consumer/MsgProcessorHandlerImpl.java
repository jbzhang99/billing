package com.ai.runner.center.bmc.deduct.consumer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.runner.center.bmc.deduct.service.interfaces.Ideduct;
public class MsgProcessorHandlerImpl implements IMsgProcessorHandler{
	private ApplicationContext context;
	public MsgProcessorHandlerImpl(ApplicationContext context) {
		this.context = context;
	}
	//@Override
	public IMessageProcessor[] createInstances(int partitionNum) {
		List<IMessageProcessor> processors = new ArrayList<IMessageProcessor>();
		IMessageProcessor processor = null;
		for (int i = 0; i < partitionNum; i++) {
			processor = new MessageProcessorImpl((Ideduct)context.getBean("deductImpl"));
			processors.add(processor);
		}
		return processors.toArray(new IMessageProcessor[processors.size()]);
	}
}
