package com.ai.runner.center.bmc.core;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.runner.base.exception.CallerException;
import com.ai.runner.center.bmc.dao.interfaces.BlUserinfoMapper;
import com.ai.runner.center.bmc.dao.mapper.bo.BlUserinfo;
import com.ai.runner.center.bmc.dao.mapper.bo.BlUserinfoCriteria;
import com.ai.runner.center.bmc.util.KafkaUtil;
import com.ai.runner.center.bmc.util.PropertiesUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ResAccount {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   private static final Logger log = LogManager.getLogger(ResAccount.class);
//   private IBlUserInfoSvc iBlUserInfoSvc;
   
   public static final String[] PATH = { "classpath:context/core-context.xml" };
   public static ClassPathXmlApplicationContext context;
   public static ResAccount r = null;

   public String setResAccount() throws CallerException{
       
       JSONObject userInfoObject = new JSONObject();
       KafkaUtil kafka = new KafkaUtil();
       
       String mdsns = PropertiesUtil.getValue("batch.topic");
       IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
       
       System.out.println(JSONArray.fromObject(msgSender).toString());
       
       BlUserinfoMapper blUserinfoMapper = sqlSessionTemplate.getMapper(BlUserinfoMapper.class);
       BlUserinfoCriteria blUserinfoCriteria = new BlUserinfoCriteria();
       int i = 0;
       while(true){
           
           blUserinfoCriteria.setLimitStart(i);
           blUserinfoCriteria.setLimitEnd(50);
           System.out.println(""+i);
           List<BlUserinfo>userInfoList =blUserinfoMapper.selectByExample(blUserinfoCriteria); 
           if(userInfoList.size()>0){
               for(BlUserinfo r : userInfoList){
                   //System.out.println("SUBS_ID="+r.getSubsId()+" ;TENANT_ID="+r.getTenantId()+" ;MSG_TYPE=USR ;SERVICE_STATUS="+r.getUserState());
                   userInfoObject.put("SUBS_ID", r.getSubsId());
                   userInfoObject.put("TENANT_ID", r.getTenantId());
                   userInfoObject.put("MSG_TYPE", "BATCH");//表示来自bl_userinfo表的触发消息
                   userInfoObject.put("USER_STATE",r.getUserState());
                   /*
                    *  传到kafka队列
                    */                 
                   //log.info("SUBS_ID="+r.getSubsId()+" ;TENANT_ID="+r.getTenantId()+" ;MSG_TYPE=USR ;SERVICE_STATUS="+r.getUserState());
                   int part=i%2;
                   System.out.println(userInfoObject.toString());
                   msgSender.send(userInfoObject.toString(), part);//第二个参数为分区键，如果不分区，传入0
                   //kafka.addQueue(userInfoObject.toString());               
               }
           }
           
           //判断是否读取完整张表 break
           if(userInfoList.size() < 49){
               int num=i+userInfoList.size();
               System.out.println("总共传入"+num+"条用户信息");
               break;
           }
           i = i + 50;
       }
       return null;
   }
   
   public static void main(String[] args){
       // 启动spring容器
       log.info("启动spring容器,配置文件路径为"+PATH[0]);
       System.out.println("启动spring容器,配置文件路径为"+PATH[0]);
       context = new ClassPathXmlApplicationContext(PATH);
       context.registerShutdownHook();
       context.start();
       PropertiesUtil.load("batch.properties");
       r = context.getBean(ResAccount.class);
       r.setResAccount();
       System.out.println("finish");
       
//       QuartzManager.addJob("job1", "com.ai.runner.center.bmc.core.myJob", "0/5 * * * * ?");
//       QuartzManager.startJobs();
       }


}
