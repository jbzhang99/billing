package com.ai.baas.bmc.srv.flow.output;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;

@Component
public class MDSClient {

	private static Logger logger = LoggerFactory.getLogger(MDSClient.class);
	private static final String bmc_service_output_queue = "baas_bmc_service_out_queue";
	private static IMessageSender msgSender = null;
	
	@PostConstruct
	public void startup(){
		try{
			msgSender = MDSClientFactory.getSenderClient(bmc_service_output_queue);
		}catch(Exception e){
			logger.error("error",e);
		}
	}
	
	public static IMessageSender getSender(){
		return msgSender;
	}
	
}
