package com.ai.baas.batch.deduct.client.core;

import com.ai.baas.batch.deduct.client.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.batch.deduct.client.service.IBlAcctInfoSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("deductScheduler")
public class DeductAccount {

   @Resource
   private IBlAcctInfoSV iBlAcctInfoSV;
   private static final Logger LOG = LogManager.getLogger(DeductAccount.class);

   public static final String[] PATH = { "classpath:context/core-context.xml" };
   public static ClassPathXmlApplicationContext context;
   public static final String DEDUCT_ACCT_TOPIC = "BaaS_AMC_WO_MDS";
   public static final int PAGESIZE = 50;
   public static DeductAccount r = null;

   public void setResAccount() throws SystemException{
       
       JSONObject acctInfoObject = new JSONObject();

       IMessageSender msgSender = MDSClientFactory.getSenderClient(DEDUCT_ACCT_TOPIC);
       
       int start = 0;
       while(true){
           
           List<BlAcctInfo> acctList =iBlAcctInfoSV.getAcctList(start,PAGESIZE);
           if(acctList.size()>0){
               for(BlAcctInfo r : acctList){
                   acctInfoObject.put("acct_id", r.getAcctId());
                   acctInfoObject.put("tenant_id", r.getTenantId());
                   int part=start%2;
                   LOG.info(acctInfoObject.toString());
                   msgSender.send(acctInfoObject.toString(), part);//第二个参数为分区键，如果不分区，传入0
               }
           }
           
           //判断是否读取完整张表 break
           if(acctList.size() < 49){
               int num=start+acctList.size();
               LOG.error("总共传入"+num+"条用户信息");
               break;
           }
           start = start + 50;
       }
   }
   
   public static void main(String[] args){
        // 启动spring容器
        LOG.info("启动spring容器,配置文件路径为"+PATH[0]);
        context = new ClassPathXmlApplicationContext(PATH);
        context.registerShutdownHook();
        context.start();
        r = context.getBean(DeductAccount.class);
        r.setResAccount();
        LOG.info("发送消息至销账队列完毕");
   }
}
