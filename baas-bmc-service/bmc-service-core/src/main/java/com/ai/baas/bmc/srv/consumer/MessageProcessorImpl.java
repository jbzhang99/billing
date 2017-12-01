package com.ai.baas.bmc.srv.consumer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.executor.MessageHandler;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;

public class MessageProcessorImpl implements IMessageProcessor {
	private static Logger logger = LoggerFactory.getLogger(MessageProcessorImpl.class);
	
	@Override
	public void process(MessageAndMetadata message) throws Exception {
		String strMessage = new String(message.getMessage(),"UTF-8");
		//logger.info("接收到消息:"+strMessage);
		System.out.println("接收到消息:"+strMessage);
		if (StringUtils.isNotBlank(strMessage)) {
			String[] inputDatas = StringUtils.splitPreserveAllTokens(strMessage,BaasConstants.RECORD_SPLIT);
			for(String inputData : inputDatas){
				MessageHandler.addMsgToQueue(inputData);
			}
		}
	};

}
