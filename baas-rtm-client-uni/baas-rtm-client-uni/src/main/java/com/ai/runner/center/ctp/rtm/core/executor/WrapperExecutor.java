package com.ai.runner.center.ctp.rtm.core.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class WrapperExecutor {

	private static final int DEFAULT_MAX_POOL_SIZE = 16;
    private static final int BASE_THREAD_NUMBER = 1;
    private static final long DEFAULT_KEEPALIVE_TIME = 10;
    private static final int DEFAULT_QUEUE_SIZE = 10;
	
	private static int corePoolSize;
	private static int maxPoolSize;
	private static int keepAliveTime;
	private static int blockingQueueSize;
	private BlockingQueue<Runnable> queue;
	private static ThreadPoolExecutor executor;
	
	private WrapperExecutor(){
	}
	
	static{
		String strCorePoolSize = PropertiesUtil.getValue("ctp.rtm.executor.corePoolSize");
		if(StringUtils.isNotBlank(strCorePoolSize)){
			corePoolSize = Integer.parseInt(strCorePoolSize);
		}
		String strMaxPoolSize = PropertiesUtil.getValue("ctp.rtm.executor.maxPoolSize");
		if(StringUtils.isNotBlank(strMaxPoolSize)){
			maxPoolSize = Integer.parseInt(strMaxPoolSize);
		}
		String strKeepAliveTime = PropertiesUtil.getValue("ctp.rtm.executor.keepAliveTime");
		if(StringUtils.isNotBlank(strKeepAliveTime)){
			keepAliveTime = Integer.parseInt(strKeepAliveTime);
		}
		String strBlockingQueueSize = PropertiesUtil.getValue("ctp.rtm.executor.blockingQueueSize");
		if(StringUtils.isNotBlank(strBlockingQueueSize)){
			blockingQueueSize = Integer.parseInt(strBlockingQueueSize);
		}
	}
	
	public static void create(){
		if(executor == null){
			synchronized(WrapperExecutor.class){
				if(executor == null){
					init();
				}
			}
		}
	}
	
	private static void init(){
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(blockingQueueSize);
		executor = new ThreadPoolExecutor(
				corePoolSize, 
				maxPoolSize, 
				keepAliveTime, 
				TimeUnit.SECONDS, queue,
				new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	public static void execute(Runnable runnable){
		executor.execute(runnable);
	}
	
	
}
