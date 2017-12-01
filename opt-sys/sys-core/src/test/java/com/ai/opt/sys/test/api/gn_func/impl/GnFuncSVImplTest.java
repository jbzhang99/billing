package com.ai.opt.sys.test.api.gn_func.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sys.api.gnfunc.interfaces.IGnFuncManageSV;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageResponse;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class GnFuncSVImplTest {
    @Autowired
    IGnFuncManageSV iGnFuncManageSV;
    @Test
    public void getGnFuncPage(){
        QueryGnFuncPageRequest request = new QueryGnFuncPageRequest();
        request.setPageSize(10);
        request.setPageNo(1);
        QueryGnFuncPageResponse p= iGnFuncManageSV.queryFuncPageInfo(request);
        System.out.println("result="+JSON.toJSONString(p.getPageInfo().getResult()));
    }
    @Test
    public void getGnFunc(){
        QueryGnFuncInfoRequest params=new QueryGnFuncInfoRequest();
        params.setFuncId(1L);
		QueryGnFuncResponse re=iGnFuncManageSV.queryFuncInfo(params);
        System.out.println("result="+JSON.toJSONString(re));
    }
}
