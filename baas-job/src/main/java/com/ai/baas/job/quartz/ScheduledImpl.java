package com.ai.baas.job.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledImpl {
//    @Scheduled(cron = "0/5 * * * * *")  
//    @Scheduled(fixedRate = 5000)
    void doSomethingWith(){  
        System.out.println("I'm doing with cron now!");  
    }  
}
