package com.ai.runner.center.bmc.resdeposit.application;

import java.util.ArrayList;
import java.util.List;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.runner.center.bmc.resdeposit.util.PropertiesUtil;

public class StartSDK {

    public void recvMsg(){
        String mdsns = PropertiesUtil.getValue("batch.topic");
        IMsgProcessorHandler msgProcessorHandler=new IMsgProcessorHandler() {
          
            @Override
            public IMessageProcessor[] createInstances(int paramInt) {
                // TODO Auto-generated method stub
                
                List<IMessageProcessor> processors = new ArrayList<>();
                IMessageProcessor processor = null;
                for (int i = 0; i < paramInt; i++) {
                    processor = new MessageProcessorImpl();
                    processors.add(processor);
                }
                return processors.toArray(new IMessageProcessor[processors.size()]);
                
            }
        };
        IMessageConsumer msgConsumer= MDSClientFactory.getConsumerClient(mdsns, msgProcessorHandler);
        msgConsumer.start();
        synchronized (StartSDK.class) {
            while (true) {
                try {
                    StartSDK.class.wait();

                } catch (Exception e) {
                    System.out.println("MDS 消费错误，具体信息为：" + e.getMessage());
                }
            }
        }
        
    }
    
    public static void main(String[] args){
        MessageProcessorImpl mp = new MessageProcessorImpl();
        StartSDK startSDK = new StartSDK();
        startSDK.recvMsg();
        
    }
}
