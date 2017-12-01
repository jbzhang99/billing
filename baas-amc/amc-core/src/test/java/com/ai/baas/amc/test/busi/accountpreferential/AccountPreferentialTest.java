package com.ai.baas.amc.test.busi.accountpreferential;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.service.business.interfaces.IAccountPreferentialBusiSV;
import com.ai.baas.amc.service.business.interfaces.ITotalBillBusiSV;
import com.ai.baas.amc.vo.AccountPreferentialVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class AccountPreferentialTest {
	private static final Log log = LogFactory.getLog(AccountPreferentialTest.class);
	@Autowired
	IAccountPreferentialBusiSV accountPreferentialBusiSV;
	@Autowired
    ITotalBillBusiSV totalBillBusiSV;
	@Test
	public void testAccountPreferential(){
	    AccountPreferentialVo vo = new AccountPreferentialVo();
	    vo.setAcctId("1234");
	    vo.setBillMonth("201607");
	    vo.setCustId("1234");
	    vo.setFee1("100000");
	    vo.setFee2("0");
	    vo.setFee3("0");
	    vo.setServiceId("1234");
	    vo.setSubject1("100001");
        vo.setSubject2("100001");
        vo.setSubject3("100001");
        vo.setSubsId("1234");
        vo.setTenantId("TR");
	    vo.setDrTotalAmount("100212");
	    
	    accountPreferentialBusiSV.accountPreferential(vo);
	}
	
	@Test
    public void testTotalBillBusiSV(){
	    totalBillBusiSV.totalBill("TR", "201607");
    }
}
