package com.ai.baas.smc.test.api.sysparam;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.sysparamcache.interfaces.ISmcSysParamCache;
import com.ai.baas.smc.api.sysparamcache.param.SmcSysParam;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class SysParamManageCache {

    @Autowired
    protected ApplicationContext ctx;
    
    @Autowired
    protected ISmcSysParamCache cache;

    // private ISysParamManageSV iSysParamManageSV=ctx.getBean(ISysParamManageSV.class);

    @Test
    public void querylist() {
        List<SmcSysParam> list=cache.getSysParams("MVNE", "SFTP_CONF", "USER_NAME");
        System.out.println("querylist="+JSON.toJSONString(list));
        
    }
    @Test
    public void querysingle() {
    	SmcSysParam list=cache.getSysParam("MVNE", "SFTP_CONF", "USER_NAME","pabas01");
    	System.out.println("querysingle="+JSON.toJSONString(list));
    	
    }
    @Test
    public void querydesc() {
    	String list=cache.getSysParamDesc("MVNE", "SFTP_CONF", "USER_NAME","pabas01");
    	System.out.println("querydesc="+JSON.toJSONString(list));
    	
    }

    

}
