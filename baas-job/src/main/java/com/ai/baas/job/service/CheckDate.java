package com.ai.baas.job.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ai.baas.job.util.CronExpression;
import com.ai.opt.sdk.util.StringUtil;


public class CheckDate {
    private static Logger logger = Logger.getLogger(CheckDate.class); 

    public static void main(String[] args) throws InterruptedException {

        try {

            CronExpression cronExpression1 =  new CronExpression("* * * L 1/3 ? 2015-2019");
//            CronExpression cronExpression2 =  new CronExpression("12 1 1 1 1/3 ?");
            logger.info("------------------------------");
            logger.info(cronExpression1.getExpressionSummary());
            logger.info("------------------------------");

            String days=null;
            String months=null;
            String years=null;
            
            days = cronExpression1.getExpressionSummaryDay();
            months = cronExpression1.getExpressionSummaryMonth();
            years = cronExpression1.getExpressionSummaryYear();
             logger.info(days);
             logger.info(months);
             logger.info(years);
             
            String[] dayArray = days.split(",");
            String[] monthArray = months.split(",");
            String[] yearsArray = years.split(",");
            
            logger.info("dayArray length: "+dayArray.length);
            logger.info("monthArray length: "+monthArray.length);
            logger.info("yearsArray length: "+yearsArray.length);
            
            
            Calendar c  = Calendar.getInstance();           
            logger.info(c.getActualMaximum(Calendar.DAY_OF_MONTH));
            logger.info("c.get(Calendar.YEAR)   "+c.get(Calendar.YEAR));
            for(String a:yearsArray){
                logger.info("判断year："+a.equals(String.valueOf(c.get(Calendar.YEAR))));
            }
            logger.info("c.get(Calendar.MONTH)   "+c.get(Calendar.MONTH));
            for(String a:monthArray){
                logger.info("判断MONTH："+a.equals(String.valueOf(c.get(Calendar.MONTH))));
            }
            
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),  
                    0, 0, 0);  
            Date beginOfDate = c.getTime();  
            c.add(Calendar.DAY_OF_MONTH,1);
            Date endOfDate = c.getTime();
            
            
            logger.info("-------------beginOfDate-----------:"+beginOfDate);
            logger.info("-------------endOfDate----------: "+endOfDate);
            
        } catch (ParseException e1) {
            e1.printStackTrace();
        } 
    }
}
