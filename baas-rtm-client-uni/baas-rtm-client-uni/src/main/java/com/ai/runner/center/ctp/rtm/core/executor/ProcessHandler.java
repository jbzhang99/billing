package com.ai.runner.center.ctp.rtm.core.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ai.runner.center.ctp.rtm.core.db.entity.BlUserinfo;
import com.ai.runner.center.ctp.rtm.core.processor.ProcessorFactory;
import com.ai.runner.center.ctp.rtm.core.reader.SXServiceReader;

public class ProcessHandler extends LoopThread {

	private static Logger logger = LoggerFactory.getLogger(ProcessHandler.class);
	public static BlockingQueue<List<BlUserinfo>> taksQueue = new LinkedBlockingQueue<List<BlUserinfo>>();
	private ProcessorFactory processorFactory = new ProcessorFactory();
	
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
		List<BlUserinfo> message = new ArrayList<BlUserinfo>();
		//logger.debug("---------------TaskDistributor.begin");
		try{
			message = taksQueue.take();
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
	
	private void execute(List<BlUserinfo> message){
		LoopThread processor = null;
		try {
			processor = processorFactory.getProcessorByName(SXServiceReader.readerName, message);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		WrapperExecutor.execute(processor);
	}

	

}
