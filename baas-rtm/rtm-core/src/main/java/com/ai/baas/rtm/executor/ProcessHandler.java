package com.ai.baas.rtm.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.initload.ThreadPoolInit;
import com.ai.baas.rtm.processor.JsEndProcessor;
import com.ai.baas.rtm.processor.RestProcessor;


public class ProcessHandler extends LoopThread {

	private static Logger logger = LoggerFactory.getLogger(ProcessHandler.class);
	public static BlockingQueue<String> taskQueue = new LinkedBlockingQueue<String>();

	
	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		//String message = "BAASGPRS12345weTIANRUN1231313223redwwSXCM_DATA17090181836123201604081111112345624567123410.1.234.12SXCM_DATA17090181837123201604081111112345624567123410.1.234.12";
		String message=null;
		try{
			message = taskQueue.take();
			logger.debug("the take message is "+message);
		}catch(InterruptedException e){
			logger.error("context", e);
			exitFlag = true;
		}
		try{
			if(message!=null&&!message.contains("JS0000")){
				logger.debug("the billing thread start......");
				ThreadPoolInit.execute(new RestProcessor(message));
			}else{
				if(message!=null&&message.contains("JS0000")){
					logger.debug("the js thread start......");
					ThreadPoolInit.execute(new JsEndProcessor());
				}
			}
		}catch(Exception e){
			logger.error("the work error ....",e);
		}
	}
	


	

}
