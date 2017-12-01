package com.ai.opt.uac.test.user;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.citic.uac.api.citicuser.interfaces.ILoginSV;
import com.ai.baas.citic.uac.api.citicuser.param.UserLoginResponse;
import com.ai.opt.base.exception.RPCSystemException;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:context/core-context.xml")
@ContextConfiguration({ "/dubbo/provider/dubbo-provider.xml" })
public class TestLoginSV {

	@Autowired
	ILoginSV iLoginSV;
	
	@Ignore
	@Test
    public void testSSO() throws RPCSystemException{
        String name = "user001@citic.com";
        UserLoginResponse info = iLoginSV.queryUserByUserName(name);
        System.out.println("result="+JSON.toJSONString(info));
    }
}
