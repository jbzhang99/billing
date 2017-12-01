package com.ai.baas.bmc.api.packageassemble.impl.executor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.paas.ipaas.ccs.constants.ConfigException;

@Component
public class ProcessExecutor {
	private static Logger logger = LoggerFactory.getLogger(ProcessExecutor.class);
	private int maxPoolSize = 10;
	private static ThreadPoolExecutor executor;
	
	@PostConstruct
	public void initData(){
		try {
//			String executorMaxNum = CCSClientFactory.getDefaultConfigClient().get("3");
//			if (StringUtils.isNotBlank(executorMaxNum)) {
//				maxPoolSize = Integer.parseInt(executorMaxNum);
//			}
			//System.out.println("maxPoolSize--------"+maxPoolSize);
			executor = new ThreadPoolExecutor(0, maxPoolSize, 30,
					TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
					new ThreadPoolExecutor.CallerRunsPolicy());
			logger.debug("流程处理器启动成功...");
		} catch (Exception e) {
			logger.error("error", e);
			e.printStackTrace();
		}
	}
	 

	public static void execute(Runnable runnable){
		executor.execute(runnable);
	}
	
}
