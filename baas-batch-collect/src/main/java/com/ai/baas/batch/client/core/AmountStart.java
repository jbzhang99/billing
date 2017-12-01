package com.ai.baas.batch.client.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.batch.client.basethread.LoopThread;
import com.ai.baas.batch.client.basethread.SendRtmThread;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.jcraft.jsch.Logger;

@Service
@Transactional
public class AmountStart {

    private static final String[] PATH = { "classpath:context/core-context.xml" };
    private static ClassPathXmlApplicationContext context;
    private static final Log LOG = LogFactory.getLog(AmountStart.class);
    
    //增加消息队列 用来存放 发给rtm的msg
    public static LinkedBlockingQueue<String> qRtmMsg =  new LinkedBlockingQueue<String>();
    static List<SendRtmThread> lThread ;
    
    
    public static void main (String[] args){
        try {
        	
        	
            context = new ClassPathXmlApplicationContext(PATH);
            context.registerShutdownHook();
            context.start();
            
            PropertiesUtil.load("batch.properties");
            
            String rtmUrl = getUrl.getInstance().getRtm();
            LOG.info("获取到的url地址：" + rtmUrl);
            int nThreadNum = Integer.parseInt(PropertiesUtil.getValue("batch.thread_number"));
            int nInter = Integer.parseInt(PropertiesUtil.getValue("batch.thread_inter"));
            lThread = new ArrayList<SendRtmThread>();
            for(int i=0;i < nThreadNum;i++)
            {
            	SendRtmThread sendRtm = new SendRtmThread();
            	sendRtm.setThreadName("SEND_RTM__" + i);
            	sendRtm.setInterval(nInter);
            	sendRtm.setRtmUrl(rtmUrl);
            	sendRtm.start();
            	
            	lThread.add(sendRtm);
            }
            
            
            LOG.info("【单独进行使用量批处理】");
           // GetRecordDetail recordDetail=new GetRecordDetail();
            GetRecordDetail recordDetail=context.getBean(GetRecordDetail.class);
            recordDetail.recordFromZX();
            LOG.error("获取话单完成，开始转入退出步骤！");
            //完成放入消息队列后  开始设置退出标志
            for(int i=0 ;i<lThread.size();i++)
            {
            	lThread.get(i).setExitFlag(true);
            }
            boolean exitFlag = true;
            while(true)
            {
            	//判断状态  是否退出
            	exitFlag = true;
            	for(int i=0;i<lThread.size();i++)
            	{
            		LOG.debug("线程状态：" + lThread.get(i).getState());
            		if(lThread.get(i).getState() != Thread.State.TERMINATED)
            			exitFlag = false;
            	}
            	if(exitFlag)
            		break;
            	else
            	{
            		Thread.sleep(2000);
            	}
            }
            LOG.error("任务处理完成，全部线程退出完成！");
            context.close();
            System.exit(0);
            
            
        }catch(Exception e) {
            LOG.error("【使用量批处理错误："+e.getMessage()+"】",e);
        }finally{
            context.close();
        }
       
    }
}
  