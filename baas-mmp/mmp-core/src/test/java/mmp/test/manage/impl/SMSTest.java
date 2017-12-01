package mmp.test.manage.impl;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sdk.util.UUIDUtil;
import com.ai.runner.center.mmp.api.manager.interfaces.SMSServices;
import com.ai.runner.center.mmp.api.manager.param.SMData;
import com.ai.runner.center.mmp.api.manager.param.SMDataInfoNotify;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class SMSTest 
{

	@Autowired
	private SMSServices sms;
	
	@Test
	public void testSendSMS(){
		SMDataInfoNotify paramInfo=new SMDataInfoNotify();
		paramInfo.setTenantId("0");
		paramInfo.setSystemId(UUIDUtil.genId32());
		paramInfo.setMsgSeq("1");
		List<SMData> dataList=new ArrayList<SMData>();
		SMData d=new SMData();
		d.setTemplateId("1");
		d.setPhone("13811095237");
		d.setServiceType("1");
		//${VERIFY}为验证码   ${VALIDMINS}：有效分钟数
		d.setGsmContent("${VERIFY}:314230^${VALIDMINS}:30");
		dataList.add(d);
		paramInfo.setDataList(dataList);
		System.out.println("param:"+JSON.toJSONString(paramInfo));
		String res=sms.dataInput(paramInfo);
		System.out.println("res:"+res);
		
	}
}