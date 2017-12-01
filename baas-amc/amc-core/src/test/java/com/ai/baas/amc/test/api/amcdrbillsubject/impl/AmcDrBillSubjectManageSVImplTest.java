package com.ai.baas.amc.test.api.amcdrbillsubject.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectManageSV;
import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectQuerySV;
import com.ai.baas.amc.api.amcdrbillsubject.param.AmcDrBillSubjectRelatedParamVO;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectParamVO;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectResponse;
import com.ai.baas.amc.api.amcdrbillsubject.param.QueryDrSubjectParamVO;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class AmcDrBillSubjectManageSVImplTest {
	private static final Log log = LogFactory.getLog(AmcDrBillSubjectManageSVImplTest.class);
	@Autowired
	private IAmcDrBillSubjectManageSV iAmcDrBillSubjectManageSV;
	@Test
	public void testAddAmcDrBillSubject(){
		AmcDrBillSubjectRelatedParamVO vo = new AmcDrBillSubjectRelatedParamVO();
		vo.setTenantId("111");
		vo.setBillSubjectId("111");
		//
		List<DrSubjectParamVO> drSubjectParamVOList = new ArrayList<DrSubjectParamVO>();
		DrSubjectParamVO drSubjectParamVO = new DrSubjectParamVO();
		drSubjectParamVO.setDrSubjectId("2424");
		drSubjectParamVOList.add(drSubjectParamVO);
		//
		vo.setDrSubjectParamVOList(drSubjectParamVOList);
		//
		
		this.iAmcDrBillSubjectManageSV.delAmcDrBillSubject(vo);
		this.iAmcDrBillSubjectManageSV.addAmcDrBillSubject(vo);
		
		log.info("---------------param---->>>"+JSON.toJSONString(vo));
	}
}
