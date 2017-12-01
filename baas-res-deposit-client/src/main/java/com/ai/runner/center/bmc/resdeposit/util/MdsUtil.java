package com.ai.runner.center.bmc.resdeposit.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class MdsUtil {
    private static final Logger LOG = LogManager.getLogger(MdsUtil.class);

    private static final String PATH = "mdsAuth.properties";

    private static final Properties propModel;

    private static IMessageSender sender;

    static {
        propModel = new Properties();
        try {
            propModel.load(MdsUtil.class.getClassLoader().getResourceAsStream(PATH));
        } catch (IOException e) {
            LOG.error("加载失败", e);
        }
    }

    public static IMessageSender getSender() {
        if (sender == null) {
            AuthDescriptor ad = new AuthDescriptor(propModel.getProperty("authAddr"),
                    propModel.getProperty("authPid"), propModel.getProperty("servicePasswd"),
                    propModel.getProperty("srvId"));
            sender = MsgSenderFactory.getClient(ad, propModel.getProperty("msgTopic"));

        }
        return sender;
    }
}
