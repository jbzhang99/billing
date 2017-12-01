package com.ai.baas.bmc.srv.flow.billing.lock;

import java.util.concurrent.TimeUnit;

import com.ai.baas.bmc.srv.flow.cache.CacheProxy;

/**
 * 简单分布式锁
 * @author majun
 *
 */
public class DistributedMultiLock {
	private static final int DEFAULT_SINGLE_EXPIRE_TIME = 5*60;//默认过期时间5分钟
	private static final int DEFAULT_WAIT_TIME = 500;//默认等待线程时间500毫秒
	private static final String LOCK_PREFIX = "amc:resbook:lock:";
	private static final String EXECUTE_FLAG = "1";//执行标志
	private String lock_name;
	
	public DistributedMultiLock(String lock_name){
		StringBuilder str = new StringBuilder(LOCK_PREFIX);
		this.lock_name = str.append(lock_name).toString();
	}
	
	public void acquire() throws Exception{
		//System.out.println("thread id = "+Thread.currentThread().getId());
		//System.out.println("thread name = "+Thread.currentThread().getName());
		long startTime = System.currentTimeMillis();
		for(;;){
			//增加时间判断，防止死循环 10分钟后退出
			if((System.currentTimeMillis()-startTime)/1000 > 600){
				throw new Exception("Thread is locked!");
			}
			String lockTime = String.valueOf(System.currentTimeMillis());
			String rtnValue = String.valueOf(CacheProxy.getCacheClient().setnx(lock_name, lockTime));
			//System.out.println("rtnValue = "+rtnValue.toString());
			if (rtnValue.equals(EXECUTE_FLAG)) {
				CacheProxy.getCacheClient().expire(lock_name, DEFAULT_SINGLE_EXPIRE_TIME);
				break;
			}
			TimeUnit.MILLISECONDS.sleep(DEFAULT_WAIT_TIME);
		}
		
	}
	
	public void release(){
		CacheProxy.getCacheClient().del(lock_name);
	}
	
}
