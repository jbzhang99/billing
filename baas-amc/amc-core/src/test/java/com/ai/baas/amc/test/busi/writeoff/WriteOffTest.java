package com.ai.baas.amc.test.busi.writeoff;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.service.business.interfaces.IWriteOffBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class WriteOffTest {
	private static final Log log = LogFactory.getLog(WriteOffTest.class);
	@Autowired
	IWriteOffBusiSV WriteOffSV;
	@Test
	public void testAccountPreferential(){
	    WriteOffSV.writeCore("1234", "TR");
	}
}
 