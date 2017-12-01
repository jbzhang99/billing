package com.ai.runner.center.bmc.resdeposit.vo;

import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Date: 2016年5月13日 <br>
 * 
 * @author zhoushanbin 
 * Copyright (c) 2016 asiainfo.com <br>
 */
public class GenericJobBuilder implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		for(Entry<String,Object> entry : jobDataMap.entrySet()){
			if(entry.getValue() instanceof ITask){
				((ITask)entry.getValue()).prepare();
				((ITask)entry.getValue()).execute();
				((ITask)entry.getValue()).destory();
			}
		}
		

	}
	
	
	
}
