package com.ai.baas.bmc.srv.flow.billing.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.flow.cache.CacheProxy;

/**
 * 简单分布式锁
 * @author majun
 *
 */
@Component
public class SimpleDistributedMutex {
	private static SimpleDistributedMutex instance = null;
	private static final int DEFAULT_SINGLE_EXPIRE_TIME = 5*60;//默认过期时间5分钟
	private static final int DEFAULT_WAIT_TIME = 500;//默认等待线程时间500毫秒
	private static final String LOCK_NAME = "amc-res-book-lock";
	private static final String EXECUTE_FLAG = "1";//执行标志
	private AtomicInteger lockCount = new AtomicInteger(0);
	
	public static SimpleDistributedMutex getInstance() {
		if(instance == null){
			instance = new SimpleDistributedMutex();
		}
		return instance;
	}
	
	
	@PostConstruct
	public void loadData() {
		SimpleDistributedMutex.getInstance();
	}
	
	public void acquire() throws Exception{
		//String startTime = String.valueOf(System.currentTimeMillis());
		//System.out.println("thread id = "+Thread.currentThread().getId());
		//System.out.println("thread name = "+Thread.currentThread().getName());
		for(;;){
			String lockTime = String.valueOf(System.currentTimeMillis());
			String rtnValue = String.valueOf(CacheProxy.getCacheClient().setnx(LOCK_NAME, lockTime));
			//System.out.println("rtnValue = "+rtnValue.toString());
			if (rtnValue.equals(EXECUTE_FLAG)) {
				lockCount.incrementAndGet();
				CacheProxy.getCacheClient().expire(LOCK_NAME, DEFAULT_SINGLE_EXPIRE_TIME);
				break;
			}
			long loopStartTime = System.currentTimeMillis();
			do {
				//增加时间判断，防止死循环 10分钟后退出
				if((System.currentTimeMillis()-loopStartTime)/1000 > 600){
					throw new Exception("Thread is locked!");
				}
				TimeUnit.MILLISECONDS.sleep(DEFAULT_WAIT_TIME);
			} while (lockCount.get() == 1);
		}
		
	}
	
	public void release(){
		CacheProxy.getCacheClient().del(LOCK_NAME);
		lockCount.getAndSet(0);
	}
	
	
}
