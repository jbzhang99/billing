package com.ai.baas.amc.test.busi.accountpreferential;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.service.business.interfaces.IAccountPreferentialBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class AccountPreferentialMDSTest {
	private static final Log log = LogFactory.getLog(AccountPreferentialMDSTest.class);
	@Autowired
	IAccountPreferentialBusiSV accountPreferentialBusiSV;
	@Test
	public void testAccountPreferential() throws InterruptedException{
	    Thread.sleep(Integer.MAX_VALUE);
	}
}
