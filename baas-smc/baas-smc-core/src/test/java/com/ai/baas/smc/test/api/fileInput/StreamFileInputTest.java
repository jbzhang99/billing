package com.ai.baas.smc.test.api.fileInput;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.streamfilemanage.interfaces.IStreamFileInputSV;
import com.ai.baas.smc.api.streamfilemanage.param.StreamFileParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContextTest/dubbo-consumer-context.xml" })
public class StreamFileInputTest {

    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void input() {
        IStreamFileInputSV iStreamFileInputSV = (IStreamFileInputSV) ctx
                .getBean("iStreamFileInputSV");
        StreamFileParam streamFileParam = new StreamFileParam();
        streamFileParam.setAccountPeriod("201604");
        streamFileParam.setDataObj("BIU-GZT");
        streamFileParam.setFileName("BIU_201603_SF_10test.zip");
        streamFileParam.setFilePosition("10.1.130.84:/aifs01/users/pabas01/test");
        streamFileParam.setOperDept("opt");
        streamFileParam.setOperId("wangjl");
        streamFileParam.setTenantId("BIU");

        // StreamFileParam streamFileParam=new StreamFileParam();
        // streamFileParam.setAccountPeriod("200804");
        // streamFileParam.setDataObj("MSG");
        // streamFileParam.setFileName("message_1.zip");
        // streamFileParam.setFilePosition("10.1.130.84:/aifs01/users/pabas01/test");
        // streamFileParam.setOperDept("opt");
        // streamFileParam.setOperId("mimw");
        // streamFileParam.setTenantId("MVNE");
        iStreamFileInputSV.fileInport(streamFileParam);
    }

}
