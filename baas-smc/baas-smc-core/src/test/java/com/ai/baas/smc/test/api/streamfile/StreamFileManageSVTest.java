package com.ai.baas.smc.test.api.streamfile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.streamfilemanage.interfaces.IStreamFileInputSV;
import com.ai.baas.smc.api.streamfilemanage.param.StreamFileParam;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.paas.ipaas.util.JSonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class StreamFileManageSVTest {
    @Autowired
    protected IStreamFileInputSV streamService;

    @Test
    public void importBillDetailData() {
    	StreamFileParam streamFileParam = new StreamFileParam();
    	//{"operId":"1","filePosition":"10.1.130.84:/aifs01/users/pabas01/tmp/test",
    	//"fileName":"0005_20160427103610422.zip","accountPeriod":"201604",
    	//"dataObj":"mvne-msg","tenantId":"809730CBD17648EFBAD2F4684D1EF233"}
    	streamFileParam.setAccountPeriod("201604");
    	streamFileParam.setDataObj("BIU-SF");
    	streamFileParam.setFileName("BIU_201604_chenshuliang99_20160831171346638.zip");
    	streamFileParam.setFilePosition("10.1.130.84:/aifs01/users/pabas01/tmp/test");
    	streamFileParam.setOperId("1");
    	streamFileParam.setTenantId("BIU");
		BaseResponse fileInport = streamService.fileInport(streamFileParam );
		System.out.println(JSonUtil.toJSon(fileInport));
    }
}
