package com.ai.baas.ccp.test.mds.creditcontrol;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.ccp.mds.creditcontrol.core.interfaces.ICreditControlBusiSV;
import com.ai.baas.ccp.mds.creditcontrol.vo.ScoutEventVO;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CreditControlSenderTest {
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditControlSenderTest.class);

    @Autowired
    private transient ICreditControlBusiSV creditControlBusiSV;

    @Test
    public void sendMsgTest() {
        String mdsns = "BaaS_OMC_MDS";
        ScoutEventVO eventVO = new ScoutEventVO();
        eventVO.setEvent_id("2");
        eventVO.setSystem_id("test-system");
        eventVO.setTenant_id("ECITIC");
        eventVO.setSource_type("bmc");
        eventVO.setOwner_type("subs");
        eventVO.setOwner_id("543");
        eventVO.setEvent_type("CASH");
        eventVO.setAmount("1000");
        eventVO.setAmount_mark("MINUS");
        eventVO.setAmount_type("BOOK");
        eventVO.setExpanded_info("");
        System.out.println(JSON.toJSONString(eventVO));
        IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
        // send方法的第二个参数为随机数，依据该随机数均匀往各个片区发送消息
        msgSender.send(JSON.toJSONString(eventVO), new Random(1000).nextLong());

    }
}
