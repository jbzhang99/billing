package com.ai.baas.ccp.mds.creditcontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.ccp.mds.creditcontrol.core.interfaces.ICreditControlBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;

/**
 * 信控消息处理类
 */
public class CreditControlMsgProcessorImpl implements IMessageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CreditControlMsgProcessorImpl.class);

    private transient ICreditControlBusiSV creditControlBusiSV;

    public CreditControlMsgProcessorImpl(ICreditControlBusiSV creditControlBusiSV) {
        this.creditControlBusiSV = creditControlBusiSV;
    }

    @Override
    public void process(MessageAndMetadata message) throws Exception {
        if (null == message) {
            return;
        }
        String content = new String(message.getMessage(), "UTF-8");
        logger.info("--Topic:{}\r\n----key:{}\r\n----content:{}", message.getTopic(), new String(message.getKey(), "UTF-8"), content);
        try {
            this.creditControlBusiSV.excute(content);
        } catch (BusinessException e) {
            logger.error("消息处理出现异常", e);
        }
    }

}
