package com.ai.baas.ccp.mds.creditcontrol;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.constants.MdsConstants;
import com.ai.baas.ccp.mds.creditcontrol.core.interfaces.ICreditControlBusiSV;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.components.mds.base.AbstractMdsConsumer;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;

@Component
public class CreditControlConsumer extends AbstractMdsConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CreditControlConsumer.class);

    @Autowired
    private transient ICreditControlBusiSV creditControlBusiSV;

    @Override
    public void startMdsConsumer() throws Exception {
        logger.error("start CreditControlConsumer...");
        IMsgProcessorHandler msgProcessorHandler = new IMsgProcessorHandler() {
            @Override
            public IMessageProcessor[] createInstances(int paramInt) {
                List<IMessageProcessor> processors = new ArrayList<>();
                IMessageProcessor processor = null;
                for (int i = 0; i < paramInt; i++) {
                    processor = new CreditControlMsgProcessorImpl(creditControlBusiSV);
                    processors.add(processor);
                }
                return processors.toArray(new IMessageProcessor[processors.size()]);
            }
        };
        IMessageConsumer msgConsumer = MDSClientFactory.getConsumerClient(
                MdsConstants.MdsNameSpace.MDS_NS_CREDIT_CONTROL, msgProcessorHandler);
        msgConsumer.start();
        logger.error("start CreditControlConsumer success");
    }

}
