package com.ai.baas.bmc.srv.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.flow.GeneralFlow;

@Component
public class MessageHandler extends LoopThread {
	private static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	private static BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();
	private MessageHandler messageHandler;
	
	@PostConstruct
	public void startup(){
		if (messageHandler == null) {
			messageHandler = new MessageHandler();
			messageHandler.start();
			logger.debug("消息处理器启动成功...");
		}
	}
	
	public static void addMsgToQueue(String message){
		try {
			msgQueue.put(message);
		} catch (InterruptedException e) {
			logger.error("erroe", e);
		}
	}

	@Override
	public void work() {
		String element = null;
		try{
			element = msgQueue.take();
		}catch(InterruptedException e){
			logger.error("context", e);
			exitFlag = true;
		}
		//System.out.println("-----------"+element);
		//将任务拆分放入到线程池中执行。。。
		ProcessExecutor.execute(new GeneralFlow(element));
		
	}
	
	

	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

}
