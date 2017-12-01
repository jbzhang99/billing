package com.ai.baas.batch.client.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.batch.client.util.PropertiesUtil;

@Service
@Transactional
public class AmountStart {

    private static final String[] PATH = { "classpath:context/core-context.xml" };
    private static ClassPathXmlApplicationContext context;
    private static final Log LOG = LogFactory.getLog(AmountStart.class);
    
    
    public static void main (String[] args){
        try {
            context = new ClassPathXmlApplicationContext(PATH);
            context.registerShutdownHook();
            context.start();
            
            PropertiesUtil.load("batch.properties");
            
            LOG.info("【单独进行使用量批处理】");
           // GetRecordDetail recordDetail=new GetRecordDetail();
            GetRecordDetail recordDetail=context.getBean(GetRecordDetail.class);
            recordDetail.recordFromZX();
        }catch(Exception e) {
            LOG.error("【使用量批处理错误："+e.getMessage()+"】",e);
        }finally{
            context.close();
        }
       
    }
}
  