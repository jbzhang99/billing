package com.ai.baas.bmc.api.packageassemble.impl.consumer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.api.packageassemble.impl.executor.MessageHandler;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
@Component
public class MessageProcessorImpl implements IMessageProcessor {
	private static Logger logger = LoggerFactory.getLogger(MessageProcessorImpl.class);
	
	@Override
	public void process(MessageAndMetadata message) throws Exception {
		String strMessage = new String(message.getMessage(),"UTF-8");
		logger.info("接收到消息:"+strMessage);
		if (StringUtils.isNotBlank(strMessage)){
			String[] inputDatas = StringUtils.splitPreserveAllTokens(strMessage,";");
			for(String inputData : inputDatas){
				MessageHandler.addMsgToQueue(inputData);
			}
		}
	};

}
