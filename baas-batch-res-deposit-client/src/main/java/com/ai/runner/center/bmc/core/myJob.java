package com.ai.runner.center.bmc.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.runner.center.bmc.util.PropertiesUtil;

public class myJob implements Job{

	private static final Logger log = LogManager.getLogger(myJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
        if(ResAccount.context == null){
        	log.info("启动spring容器,配置文件路径为"+ResAccount.PATH[0]);
            System.out.println("启动spring容器,配置文件路径为"+ResAccount.PATH[0]);
            ResAccount.context = new ClassPathXmlApplicationContext(ResAccount.PATH);
            ResAccount.context.registerShutdownHook();
            ResAccount.context.start();
            PropertiesUtil.load("batch.properties");
        }
        if(ResAccount.r == null){
            ResAccount.r = ResAccount.context.getBean(ResAccount.class);
        }
        
        ResAccount.r.setResAccount();
        System.out.println("---------finish-----------");
	}
}
