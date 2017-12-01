package com.ai.runner.center.ctp.rtm.core.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.processor.ProcessorFactory;

public class ProcessHandler extends LoopThread {

	private static Logger logger = LoggerFactory.getLogger(ProcessHandler.class);
	public static BlockingQueue<String> taksQueue = new LinkedBlockingQueue<String>();
	private ProcessorFactory processorFactory=new ProcessorFactory() ;
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
		String message = "";
		//logger.debug("---------------TaskDistributor.begin");
		try{
			message = taksQueue.take();
//			System.out.println("message***************"+message);
		}catch(InterruptedException e){
			logger.error("context", e);
			exitFlag = true;
		}
		
		//logger.debug("---------------TaskDistributor.end");
		//System.out.println("-----"+message);
//		proxy.sendMessage(message);
		
		//WrapperExecutor.execute(new FileProcessor(message));
		execute(message);
	}
	
	private void execute(String message){
		String[] tmpSplit = StringUtils.splitPreserveAllTokens(message,"|");
		String readerName = tmpSplit[0];//reader
		String strMessage = tmpSplit[1];
		LoopThread processor = null;
		try {
			processor = processorFactory.getProcessorByName(readerName, strMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		WrapperExecutor.execute(processor);
	}

	

}
