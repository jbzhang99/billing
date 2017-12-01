package com.ai.opt.sys.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailViewResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedRequest;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class GnSubjectTest {
	
	@Autowired
	IGnSubjectQuerySV iGnSubjectQuerySV;
	
	@Test
	public void testQueryRelatedGnSubject(){
		String queryStr = "{\"tenantId\":\"7BAF6267AE2F421FA8D1E305EE35C4BA\",\"subjectId\":\"707\",\"industryCode\":\"04\"}";
		GnSubjectRelatedRequest queryInfo = JSONObject.parseObject(queryStr, GnSubjectRelatedRequest.class);
		GnSubjectRelatedDetailViewResponse resultInfo = iGnSubjectQuerySV.queryRelatedGnSubject(queryInfo);
		System.out.println(JSONObject.toJSONString(resultInfo));
	}
	
	@Test
	public void test2(){
		String queryStr = "{\"tenantId\":\"7BAF6267AE2F421FA8D1E305EE35C4BA\",\"subjectId\":\"707\",\"industryCode\":\"04\"}";
		GnSubjectDetailVo queryInfo = new GnSubjectDetailVo();
		queryInfo.setIndustryCode("04");
		queryInfo.setSubjectType("2");
		queryInfo.setTenantId("BIU");
		List<GnSubjectListResponse> resultInfo = iGnSubjectQuerySV.getGnSubjectListMayRelated(queryInfo);
		System.out.println(JSONObject.toJSONString(resultInfo));
	}
}
