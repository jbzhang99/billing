package com.ai.baas.omc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.omc.api.avoidscout.interfaces.IOmcAvoidScoutSV;
import com.ai.baas.omc.api.avoidscout.params.OmcAvoidScoutInfoVO;
import com.ai.opt.base.vo.BaseResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OmcAvoidScoutSVTest {
	@Autowired
	private IOmcAvoidScoutSV omcAvoidScoutSv;

	@Test
	public void testAdd() {
		OmcAvoidScoutInfoVO vo = new OmcAvoidScoutInfoVO();
		vo.setTenantId("ECITIC");
		vo.setExtCustId("wangjing19");
		vo.setSpecialType("stop");
		vo.setActiveTime("20170302120000");
		vo.setInactiveTime("20190302040506");
		
		//OmcAvoidScoutResponse response = omcAvoidScoutSv.addAvoidScout(vo);
		BaseResponse response =  omcAvoidScoutSv.delAvoidScout(vo);
		System.out.println("response.code:" + response.getResponseHeader().getResultCode());
		System.out.println("response.msg:" + response.getResponseHeader().getResultMessage());
	}
	
//	@Test
//	public void testUpd() {
//		//LoadConfServiceStart.main(new String[]{"D:\\Work\\AI-workspace\\YunJFSource\\AI-OPT\\baas-omc\\omc-core\\src\\main\\resources\\paas\\sdkmode\\aiopt-baas-omc.properties"});
//		
//		OmcAvoidScoutInfoVO vo = new OmcAvoidScoutInfoVO();
//		vo.setTenantId("ECITIC");
//		vo.setExtCustId("wangjing19");
//		vo.setSpecialType("URGE");
//		vo.setActiveTime("2017030212000");
//		vo.setInactiveTime("2020030212000");
//		
//		OmcAvoidScoutResponse response = omcAvoidScoutSv.updateAvoidScout(vo);
//		System.out.println("response.code:" + response.getResponseHeader().getResultCode());
//		System.out.println("response.msg:" + response.getResponseHeader().getResultMessage());
//	}
	
//	@Test
//	public void testDel() {
//		OmcAvoidScoutInfoVO vo = new OmcAvoidScoutInfoVO();
//		vo.setTenantId("ECITIC");
//		vo.setExtCustId("wangjing19");
//		vo.setSpecialType("URGE");
//		
//		BaseResponse response = omcAvoidScoutSv.delAvoidScout(vo);
//		System.out.println("response.code:" + response.getResponseHeader().getResultCode());
//		System.out.println("response.msg:" + response.getResponseHeader().getResultMessage());
//	}
}

