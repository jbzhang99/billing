package com.ai.baas.bmc.srv.persistence.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.baas.bmc.srv.executor.MessageHandler;
import com.ai.baas.bmc.srv.flow.duplicate.DuplicateCheckingConfig;



public class Startup {
	private static Logger logger = LoggerFactory.getLogger(Startup.class);
	
	@PostConstruct
	public void loading(){
		logger.debug("初始化任务开始...");
		//MessageHandler.startup();
		//DuplicateCheckingConfig.getInstance();
		logger.debug("初始化任务结束...");
	}
	
}
