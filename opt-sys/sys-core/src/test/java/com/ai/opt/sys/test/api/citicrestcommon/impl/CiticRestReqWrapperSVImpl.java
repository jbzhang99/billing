package com.ai.opt.sys.test.api.citicrestcommon.impl;

import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class CiticRestReqWrapperSVImpl {

    @Autowired
    private ICiticRestReqWrapperSV citicRestReqWrapperSV;

    @Test
    public void test(){
        UserInfoQueryVo query = new UserInfoQueryVo();
        query.setTenantId("citic");
        query.setSelectId("1");
        query.setSelectId("2");
        UserQueryResponse queryResponse = citicRestReqWrapperSV.searchUser(query);
        System.out.println(JSON.toJSONString(queryResponse));
    }

    @Test
    public void test1(){
        OrgQueryVo query = new OrgQueryVo();
        query.setTenantId("citic");
        query.setSelectType("3");
        query.setSelectId("2");
        OrgQueryResponse queryResponse = citicRestReqWrapperSV.searchOrg(query);
        System.out.println(JSON.toJSONString(queryResponse));
    }
}
