package com.ai.runner.center.ctp.rtm.core.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class WrapperExecutor {

//	private static final int DEFAULT_MAX_POOL_SIZE = 16;
//    private static final int BASE_THREAD_NUMBER = 1;
//    private static final long DEFAULT_KEEPALIVE_TIME = 10;
//    private static final int DEFAULT_QUEUE_SIZE = 10;
	
	private  Integer corePoolSize;
	private  Integer maxPoolSize;
	private  Integer keepAliveTime;
	private  Integer blockingQueueSize;
	@SuppressWarnings("unused")
	private BlockingQueue<Runnable> queue;
	private static ThreadPoolExecutor executor;
	
	public WrapperExecutor(){
	}
	
	
	public  void create(){
		if(executor == null){
			synchronized(WrapperExecutor.class){
				if(executor == null){
					init();
				}
			}
		}
	}
	
	private  void init(){
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


	public int getCorePoolSize() {
		return corePoolSize;
	}


	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}


	public int getMaxPoolSize() {
		return maxPoolSize;
	}


	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}


	public int getKeepAliveTime() {
		return keepAliveTime;
	}


	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}


	public int getBlockingQueueSize() {
		return blockingQueueSize;
	}


	public void setBlockingQueueSize(int blockingQueueSize) {
		this.blockingQueueSize = blockingQueueSize;
	}
	
	
}
