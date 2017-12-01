package com.ai.baas.amc.test.api.amcdrbillsubject.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectQuerySV;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectResponse;
import com.ai.baas.amc.api.amcdrbillsubject.param.QueryDrSubjectParamVO;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class AmcDrBillSubjectQuerySVImplTest {
	private static final Log log = LogFactory.getLog(AmcDrBillSubjectQuerySVImplTest.class);
	@Autowired
	private IAmcDrBillSubjectQuerySV iAmcDrBillSubjectQuerySV;
	@Test
	public void testQueryDrSubjectIdList(){
		QueryDrSubjectParamVO queryDrSubjectParamVo = new QueryDrSubjectParamVO();
		queryDrSubjectParamVo.setTenantId("111");
		queryDrSubjectParamVo.setBillSubjectId("111");
		//
		List<DrSubjectResponse> drSubjectResponseList = this.iAmcDrBillSubjectQuerySV.queryDrSubjectIdList(queryDrSubjectParamVo);
		log.info("------------------->>>"+JSON.toJSONString(drSubjectResponseList));
		System.out.println("------------------->>>:"+drSubjectResponseList.size());
	}
}
