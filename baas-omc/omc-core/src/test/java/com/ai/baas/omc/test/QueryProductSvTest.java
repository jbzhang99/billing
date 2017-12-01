package com.ai.baas.omc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.omc.api.rule.interfaces.IOmcRuleSV;
import com.ai.baas.omc.api.rule.params.QueryInfoParam;
import com.ai.baas.omc.api.rule.params.QueryRuleResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class QueryProductSvTest {
	@Autowired
	private IOmcRuleSV omcRuleSv;

	@Test
	public void testPageSearch() {
		QueryInfoParam param = new QueryInfoParam();
		param.setPageNo(1);
		param.setPageSize(20);
		param.setTradeSeq("1");
		QueryRuleResponse response = omcRuleSv.queryRule(param);
		System.out.println(JSON.toJSONString(response));
	}
}
