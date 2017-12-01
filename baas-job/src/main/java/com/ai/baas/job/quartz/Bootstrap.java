package com.ai.baas.job.quartz;


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.ai.baas.job.quartz.HelloJob;



public class Bootstrap {
    private static Logger logger = Logger.getLogger(Bootstrap.class); 

    public static void main(String[] args) throws InterruptedException {

        try {
            // 获取Scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // 具体任务
            JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

            // 触发时间点
//            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
            
            CronScheduleBuilder cronScheduleBuilder1 = CronScheduleBuilder.cronSchedule("15 1 1 13 9 ?");
            CronScheduleBuilder cronScheduleBuilder2 = CronScheduleBuilder.cronSchedule("* * * 13 9 ?");

            Trigger trigger1 = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder1).build();
            
            Trigger trigger2 = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder2).build();
            
            CronExpression cronExpression1 =  new CronExpression("15 * * * 1-5,9-10 ?");
//            CronExpression cronExpression2 =  new CronExpression("12 1 1 1 1/3 ?");
            logger.info("------------------------------");
            logger.info(cronExpression1.getExpressionSummary());
            logger.info("------------------------------");
            logger.info("trigger1.compareTo(trigger2) :      "+trigger2.compareTo(trigger1));

            // 交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger2);
            scheduler.pauseJob(job.getKey()); 
            logger.info("getNextFireTime : "+trigger2.getNextFireTime()); 
//            Date triggerTime = trigger2.getNextFireTime();


            Calendar c  = Calendar.getInstance();
            
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),  
                    0, 0, 0);  
            Date beginOfDate = c.getTime();  
            c.add(Calendar.DAY_OF_MONTH,1);
            Date endOfDate = c.getTime();

            logger.info("-------------beginOfDate-----------:"+beginOfDate);
            logger.info("-------------endOfDate----------: "+endOfDate);
//            if(triggerTime.after(bbb)&&triggerTime.before(aaa)){
//                logger.info("finish"); 
//            }           
            Timestamp inactiveTime = new Timestamp(System.currentTimeMillis());
            logger.info(inactiveTime.toString());
            /* 为观察程序运行，此设置主程序睡眠3分钟才继续往下运行（因下一个步骤是“关闭Scheduler”） */
            try {
                TimeUnit.MINUTES.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 关闭Scheduler
            scheduler.shutdown();

        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
        } catch (ParseException e1) {
            e1.printStackTrace();
        } 
    }

}
