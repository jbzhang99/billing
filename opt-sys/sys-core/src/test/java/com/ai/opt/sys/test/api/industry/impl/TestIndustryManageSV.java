package com.ai.opt.sys.test.api.industry.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.exception.RPCSystemException;
import com.ai.opt.sys.api.industry.interfaces.IIndustryManageSV;
import com.ai.opt.sys.api.industry.param.IndustryQueryResponse;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class TestIndustryManageSV {

	@Autowired
	IIndustryManageSV iIndustryManageSV;
	
	@Test
	public void testQueryBaseInfo() throws RPCSystemException{
		System.out.println("<<<<<<<<<<<<<<Begin testQueryBaseInfo>>>>>>>>>>>>>>");
		IndustryQueryResponse industryInfo = iIndustryManageSV.queryByIndustryCode("13");
		JSONObject fromObject = JSONObject.fromObject(industryInfo);
		System.out.println(fromObject);
		System.out.println("<<<<<<<<<<<<<<End testQueryBaseInfo>>>>>>>>>>>>>>");
	}
	
	
}
