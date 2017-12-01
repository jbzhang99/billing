package com.ai.opt.collection;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.opt.collection.model.TRUserInfo;
import com.ai.opt.collection.util.InfoUtil;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.components.mds.base.AbstractMdsConsumer;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
@Component
public class GenerateMdsConsumer extends AbstractMdsConsumer{
	private static Logger logger = LoggerFactory.getLogger(GenerateMdsConsumer.class);
	static TRUserInfo info=null;
	static{
	 info=	InfoUtil.getTR();
	}
	//启动消息监听
	@Override
	public void startMdsConsumer() throws Exception {
		logger.error("------------------------->开始启动消息监听");
		IMsgProcessorHandler msgProcessorHandler = new IMsgProcessorHandler() {
            @Override
            public IMessageProcessor[] createInstances(int paramInt) {
                List<IMessageProcessor> processors = new ArrayList<>();
                IMessageProcessor processor = null;
                for (int i = 0; i < paramInt; i++) {
                    processor = new MessageProcessImpl(info);  //初始化处理
                    processors.add(processor);
                }
                return processors.toArray(new IMessageProcessor[processors.size()]);
            }
        };
        
        
        IMessageConsumer msgConsumer = MDSClientFactory.getConsumerClient(
                "ns-baas-data-collection", msgProcessorHandler);  //天润的topic
        msgConsumer.start();
		logger.error("-------------------------->消息监听启动成功");
		
	}

}
