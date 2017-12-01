package com.ai.baas.job.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.apache.log4j.Logger;


public class HelloJob implements Job {
    
    private static Logger logger = Logger.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        
        logger.debug("===================job execute====================");
    }

}
