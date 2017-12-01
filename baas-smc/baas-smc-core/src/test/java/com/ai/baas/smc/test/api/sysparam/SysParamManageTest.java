package com.ai.baas.smc.test.api.sysparam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.sysparammanage.param.AddSysParamInfo;
import com.ai.baas.smc.api.sysparammanage.param.DeleteSysParam;
import com.ai.baas.smc.api.sysparammanage.param.QuerySysParamInfo;
import com.ai.baas.smc.api.sysparammanage.param.SysParamInfo;
import com.ai.baas.smc.service.busi.interfaces.ISysParamManageBusiSV;
import com.ai.opt.base.vo.PageInfo;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class SysParamManageTest {

    @Autowired
    protected ApplicationContext ctx;

    // private ISysParamManageSV iSysParamManageSV=ctx.getBean(ISysParamManageSV.class);

    @Test
    public void add() {
        ISysParamManageBusiSV iSysParamManageBusiSV = ctx.getBean(ISysParamManageBusiSV.class);
        AddSysParamInfo addSysParamInfo = new AddSysParamInfo();
        addSysParamInfo.setTenantId("baas-1");
        addSysParamInfo.setColumnDesc("columnDesc2");
        addSysParamInfo.setColumnValue("columnValue2");
        addSysParamInfo.setDescb("descb2");
        addSysParamInfo.setDispord(1);
        addSysParamInfo.setParamCode("paramCode2");
        addSysParamInfo.setParentValue("parentValue2");
        addSysParamInfo.setSubParamCode("subParamCode2");
        addSysParamInfo.setUpdateDeptId("updateDeptId2");
        addSysParamInfo.setUpdateOperId("updateOperId2");
        addSysParamInfo.setTypeCode("typeCode2");
        iSysParamManageBusiSV.addSysParam(addSysParamInfo);
        String string = JSON.toJSONString(addSysParamInfo);
        System.out.print(string);
        
    }

    @Test
    public void delete() {
        ISysParamManageBusiSV iSysParamManageBusiSV = ctx.getBean(ISysParamManageBusiSV.class);
        DeleteSysParam deleteSysParam = new DeleteSysParam();
        deleteSysParam.setGuidkey("85BF0A62DC3F4861B4F9917CACE13A4D");
        deleteSysParam.setTenantId("baas-1");
        String string = JSON.toJSONString(deleteSysParam);
        System.out.print(string);
        iSysParamManageBusiSV.deleteSysParam(deleteSysParam);
    }

    @Test
    public void update() {
        ISysParamManageBusiSV iSysParamManageBusiSV = ctx.getBean(ISysParamManageBusiSV.class);
        SysParamInfo sysParamInfo = new SysParamInfo();
        sysParamInfo.setGuidkey("2BAE738555E44DD1A2371376B91C30CB");
        sysParamInfo.setTenantId("baas-1");
        sysParamInfo.setColumnDesc("test");
 //      sysParamInfo.setColumnValue("");
//        sysParamInfo.setDescb("descbGai");
//        sysParamInfo.setDispord(1);
//        sysParamInfo.setParamCode("paramCodeGai");
//        sysParamInfo.setParentValue("parentValueGai");
//        sysParamInfo.setSubParamCode("subParamCodeGai");
//        sysParamInfo.setUpdateDeptId("updateDeptIdGai");
//        sysParamInfo.setUpdateOperId("updateOperIdGai");
//        sysParamInfo.setTypeCode("typeCodeGai");
        String string = JSON.toJSONString(sysParamInfo);
        System.out.print(string);
        iSysParamManageBusiSV.updateSysParam(sysParamInfo);
    }

    @Test
    public void query() {
        ISysParamManageBusiSV iSysParamManageBusiSV = ctx.getBean(ISysParamManageBusiSV.class);
        QuerySysParamInfo querySysParamInfo = new QuerySysParamInfo();
        querySysParamInfo.setTenantId("baas-1");
        PageInfo<SysParamInfo> page = new PageInfo<SysParamInfo>();
        page.setPageNo(1);
        page.setPageSize(2);
        querySysParamInfo.setPageInfo(page);
        String string = JSON.toJSONString(querySysParamInfo);
        System.out.print(string);
        iSysParamManageBusiSV.querySysParam(querySysParamInfo);
        // PageInfo.getPageCount();
        // System.out.print(PageInfo);
    }
    @Test
    public void test(){
        String string="";
        System.out.println(string==null);
    }

}
