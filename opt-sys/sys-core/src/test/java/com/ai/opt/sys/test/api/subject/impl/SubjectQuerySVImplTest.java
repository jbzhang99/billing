package com.ai.opt.sys.test.api.subject.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sys.api.subject.interfaces.ISubjectQuerySV;
import com.ai.opt.sys.api.subject.param.SubjectFundQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectIdParam;
import com.ai.opt.sys.api.subject.param.SubjectNameQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryByTypeResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectTypeParam;
import com.alibaba.fastjson.JSON;

/**
 * 科目查询测试类
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class SubjectQuerySVImplTest {
    
    private static final Log LOG = LogFactory.getLog(SubjectQuerySVImplTest.class);
    
    @Autowired
    private ISubjectQuerySV subjectQuerySV;

    @Test
    public void testGetSubjectName() {
        SubjectIdParam param = new SubjectIdParam();
        param.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        param.setSubjectId(100000l);
        LOG.info(JSON.toJSONString(param));
        SubjectNameQueryResponse response = subjectQuerySV.getSubjectName(param);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
        Assert.assertEquals(response.getSubjectName(), "普通预存款");
    }

    @Test
    public void testGetSubject() {
        SubjectIdParam param = new SubjectIdParam();
        param.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        param.setSubjectId(100000l);
        LOG.info(JSON.toJSONString(param));
        SubjectQueryResponse response = subjectQuerySV.getSubject(param);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
        Assert.assertNotNull(response.getSubject());
        Assert.assertEquals(response.getSubject().getSubjectName(), "普通预存款");
    }

    @Test
    public void testQuerySubjectByType() {
        SubjectTypeParam param = new SubjectTypeParam();
        param.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        param.setSubjectType("9");
        LOG.info(JSON.toJSONString(param));
        SubjectQueryByTypeResponse response = subjectQuerySV.querySubjectByType(param);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
    }

    @Test
    public void testGetSubjectFund() {
        SubjectIdParam param = new SubjectIdParam();
        param.setTenantId("4FA2FBBD1F6E49138E02ADF70D44FB4C");
        param.setSubjectId(100000l);
        LOG.info(JSON.toJSONString(param));
        SubjectFundQueryResponse response = subjectQuerySV.getSubjectFund(param);
        LOG.info(JSON.toJSONString(response));
        Assert.assertNotNull(response.getResponseHeader());
        Assert.assertEquals(response.getResponseHeader().isSuccess(), true);
        Assert.assertNotNull(response.getSubjectFund());
        Assert.assertEquals(response.getSubjectFund().getSubjectName(), "普通预存款");
    }

}
