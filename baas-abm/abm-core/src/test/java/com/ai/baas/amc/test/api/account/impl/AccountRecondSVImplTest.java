package com.ai.baas.amc.test.api.account.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.abm.api.account.interfaces.IAccountRecordSV;
import com.ai.baas.abm.api.account.params.AmcResBookVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class AccountRecondSVImplTest {

	@Autowired
	IAccountRecordSV accountRecordSV;
	
	@Test
	public void saveTest(){
		AmcResBookVo bookVo = new AmcResBookVo();
		bookVo.setEffectDate("2016-09-09 12:11:11");
		bookVo.setExpireDate("2016-03-09 12:11:11");
		bookVo.setTotalAmount(989L);
		bookVo.setSourceId(7817283L);
		bookVo.setAccountPeriod("201609");
		bookVo.setUseFlag("1");
		bookVo.setTenantId("PUB");
		accountRecordSV.saveAccountRecord(bookVo);
	}
	
	@Test
	public void clearTest(){
		
		accountRecordSV.clearExpireAccountRecord();
	}
}
