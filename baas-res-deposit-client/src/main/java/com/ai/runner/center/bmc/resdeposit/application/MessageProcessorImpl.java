package com.ai.runner.center.bmc.resdeposit.application;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.runner.center.bmc.resdeposit.service.ReEnterHandle;
import com.ai.runner.center.bmc.resdeposit.util.PropertiesUtil;


public class MessageProcessorImpl implements IMessageProcessor{
    
    private static final String[] PATH = { "classpath:context/core-context.xml" };
    private static ExecutorService executor ;
    private static ClassPathXmlApplicationContext context;
    private EnterAccount account;
    
    
    
    static{
        executor = Executors.newFixedThreadPool(1);
        
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
            
        });
        
        // 启动spring容器
        context = new ClassPathXmlApplicationContext(PATH);
        context.registerShutdownHook();
        context.start();       
        
        PropertiesUtil.load("batch.properties");
        
        //启动异常处理器
        ReEnterHandle handle = context.getBean(ReEnterHandle.class);
        handle.init();
        handle.start();
    }

	@Override
	public void process(MessageAndMetadata message) throws Exception {
	    
	    account = new EnterAccount();
		// TODO Auto-generated method stub
		if (null != message) {
			System.err.println("------Topic:" + message.getTopic() + ",key:"+ new String(message.getKey(), "UTF-8") + ",content:"+ new String(message.getMessage(), "UTF-8"));			
			String msg = new String(message.getMessage(), "UTF-8");

			executor.submit(new ConsumerThread(msg, context.getBean(EnterAccount.class)));
			
			
//			try {
//    			Map<String, String> result = gson.fromJson(msg, new TypeToken<Map<String, String>>() {}.getType());
//    			
//    			LoggerUtil.log.debug("{}获得kafka消息：[{}]",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS),msg);
//                if ("USR".equals(result.get("MSG_TYPE"))) {
//                    account.userStrike(MyJsonUtil.toBean(msg, UserMsg.class));
//                } else if ("COM".equals(result.get("MSG_TYPE"))) {
//                    account.commStrike(MyJsonUtil.toBean(msg, CommMsg.class));
//                } else if ("BATCH".equals(result.get("MSG_TYPE"))){
////                    UserMsg um = new UserMsg();
////                    um.setMSG_TYPE(result.get("MSG_TYPE"));
////                    um.setSERVICE_STATUS(result.get("USER_STATE"));
////                    um.setSUBS_ID(result.get("SUBS_ID"));
////                    um.setTENANT_ID(result.get("TENANT_ID"));
////                    System.err.println(JSONArray.toJSONString(um));
//                    account.batchStrike(result);
//                }	
//		    }catch (Exception e) {
//            System.out.println("get msg in stream error!");
//            LoggerUtil.log.error("获得kafka消息 失败:",e);
//            ReEnterHandle.pushToStatic(msg, e.getMessage());
//            }
			
	 }
	}

}
