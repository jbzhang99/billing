package com.ai.baas.job.service.busi.impl;




import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.job.dao.mapper.bo.BlSubscommExt;
import com.ai.baas.job.dao.mapper.bo.BlSubscommExtCriteria;
import com.ai.baas.job.dao.mapper.factory.MapperFactory;
import com.ai.baas.job.service.busi.MDSOutput;
import com.ai.baas.job.service.busi.interfaces.ICiticPackageScanBusiSV;
import com.ai.baas.job.util.CronExpression;
import com.ai.baas.job.util.PropertiesUtil;
import com.alibaba.fastjson.JSONObject;

@Service
@Transactional
public class CiticPackageScanBusiSVImpl implements ICiticPackageScanBusiSV{
    
    private  static Logger logger = Logger.getLogger(CiticPackageScanBusiSVImpl.class);
    @Override
    public String scan() {
//        BlSubsCommCriteria example=new BlSubsCommCriteria();
//        BlSubsCommCriteria.Criteria criteria=example.createCriteria();
//        criteria.andProductIdEqualTo(productId);      
        MDSOutput mds = new MDSOutput();
        BlSubscommExtCriteria subsExtExample = new BlSubscommExtCriteria();
        BlSubscommExtCriteria.Criteria subsExtCriteria = subsExtExample.createCriteria();
        subsExtCriteria.andExtNameEqualTo("crontab");
        List<BlSubscommExt> blSubscommExtList = new ArrayList<>();
        blSubscommExtList = MapperFactory.getBlSubscommExtMapper().selectByExample(subsExtExample);
        
        if(blSubscommExtList.size()==0){
            logger.info("未找到需要处理的定时任务");
            return null;
        }
        int num = 0;
        logger.info("analysed crontab");
        for(BlSubscommExt b : blSubscommExtList){
            
            try {
                if(checkCronDate(b.getExtValue())){
                    JSONObject msg = new JSONObject();
                    msg.put("priceCode", b.getProductId());
                    if(!PropertiesUtil.getValue("job.package.accountperiod").isEmpty()){
                        String accountPeriod = PropertiesUtil.getValue("job.package.accountperiod").trim().substring(0, 6); 
                        msg.put("account_period", accountPeriod);
                        logger.info("account_period: "+accountPeriod); 
                    }
                    logger.info("CRONTAB MATCH!! productId: "+b.getProductId()); 
                    logger.info("Sending msg to MDS.... :"+msg.toJSONString());
                    mds.sendMsg(msg.toJSONString());
                    num++;
                }else{
                    logger.info("CRONTAB DISMATCH");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
        }
        logger.info("scanning complete,  valuable msg :"+num);
        
        return null;
    }
    private boolean checkCronDate(String extValue) throws ParseException {    
       Calendar c  = Calendar.getInstance();  
       String today = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
       String thisMonth = String.valueOf(c.get(Calendar.MONTH)+1);
       String thisYear = String.valueOf(c.get(Calendar.YEAR));   
       // 识别本地日期
       if(!PropertiesUtil.getValue("job.package.year").isEmpty()){
           thisYear = PropertiesUtil.getValue("job.package.year").trim();
       }
       if(!PropertiesUtil.getValue("job.package.month").isEmpty()){
           thisMonth = PropertiesUtil.getValue("job.package.month").trim();
       }
       if(!PropertiesUtil.getValue("job.package.day").isEmpty()){
           today = PropertiesUtil.getValue("job.package.day").trim();
       }
       boolean dayMatch = false;
       boolean monthMatch = false;
       boolean yearMatch = false;
       logger.info("day: "+today+" ,month: "+thisMonth+",year:"+thisYear); 
       String cron = extValue;
       logger.info("crontab: "+cron);
       CronExpression cronExpression =  new CronExpression(cron);
       logger.info(cronExpression.getExpressionSummary());
       
       String[] dayArray = cronExpression.getExpressionSummaryDay().split(",");
       String[] monthArray = cronExpression.getExpressionSummaryMonth().split(",");
       String[] yearsArray = cronExpression.getExpressionSummaryYear().split(",");     
       logger.info("dayArray length: "+dayArray.length);
       logger.info("monthArray length: "+monthArray.length);
       logger.info("yearsArray length: "+yearsArray.length);

       for(String a:dayArray){
           if(a.equals("L")){
                   a = String.valueOf(c.getActualMaximum(Calendar.DAY_OF_MONTH));
           }
           if(a.equals(today)||a.equals("*")){
               dayMatch = true;
               logger.info("day match");
               break;
           }
       }
       for(String a:monthArray){
           if(a.equals(thisMonth)||a.equals("*")){
               monthMatch = true;
               logger.info("month match");
               break;
           }
       }
      for(String a:yearsArray){
          if(a.equals(thisYear)||a.equals("*")){
              yearMatch = true;
              logger.info("year match");
              break;
          }          
      }
      logger.info(dayMatch&&monthMatch&&yearMatch);
        return dayMatch&&monthMatch&&yearMatch;
    }

}
