package com.ai.baas.smc.test.api.fileInput;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.streamfilemanage.interfaces.IStreamFileInputSV;
import com.ai.baas.smc.api.streamfilemanage.param.StreamFileParam;
import com.ai.baas.smc.service.busi.interfaces.IStreamFileInputBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class LocalTest {
    @Autowired
    protected ApplicationContext ctx;
    
    @Test
    public void testFileInport(){
        StreamFileParam streamFileParam=new StreamFileParam();
        streamFileParam.setAccountPeriod("201604");
        streamFileParam.setDataObj("BIU-SF");
        streamFileParam.setFileName("SF_order_10.zip");
        streamFileParam.setFilePosition("10.1.130.84:/aifs01/users/pabas01/test");
        streamFileParam.setOperDept("opt");
        streamFileParam.setOperId("wangjl");
        streamFileParam.setTenantId("BIU");
        ctx.getBean(IStreamFileInputSV.class).fileInport(streamFileParam);
        //ctx.getBean(IStreamFileInputBusiSV.class).inport(streamFileParam);
        
    }
}
