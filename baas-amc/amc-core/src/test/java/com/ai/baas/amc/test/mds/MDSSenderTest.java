package com.ai.baas.amc.test.mds;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.service.business.interfaces.IAccountPreferentialBusiSV;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class MDSSenderTest {
	private static final Log log = LogFactory.getLog(MDSSenderTest.class);
	@Autowired
	IAccountPreferentialBusiSV accountPreferentialBusiSV;
	@Test
	public void testAPMDS(){
	    JSONObject json = new JSONObject();
	    json.put(AmcConstants.APMessage.ACCT_ID, "1234");
        json.put(AmcConstants.APMessage.SUBS_ID, "1234");
        json.put(AmcConstants.APMessage.CUST_ID, "1234");
        json.put(AmcConstants.APMessage.FEE1, 200000);
        json.put(AmcConstants.APMessage.FEE2, 0);
        json.put(AmcConstants.APMessage.FEE3, 0);
        json.put(AmcConstants.APMessage.SUBJECT1, "100001");
        json.put(AmcConstants.APMessage.SUBJECT2, "100001");
        json.put(AmcConstants.APMessage.SUBJECT3, "100001");
        json.put(AmcConstants.APMessage.TENANT_ID, "TR");
        
        JSONObject jsonChild1 = new JSONObject();
        jsonChild1.put(AmcConstants.APMessage.Apportion.SERVICE_ID, "1234");
        jsonChild1.put(AmcConstants.APMessage.Apportion.METHOD, AmcConstants.APMessage.Apportion.METHOD_RATIO);
        jsonChild1.put(AmcConstants.APMessage.Apportion.VALUE, 0.4);
        
        JSONObject jsonChild2 = new JSONObject();
        jsonChild2.put(AmcConstants.APMessage.Apportion.SERVICE_ID, "1001");
        jsonChild2.put(AmcConstants.APMessage.Apportion.METHOD, AmcConstants.APMessage.Apportion.METHOD_RATIO);
        jsonChild2.put(AmcConstants.APMessage.Apportion.VALUE, 0.6);
        JSONArray arr = new JSONArray();
        arr.add(jsonChild1);
        arr.add(jsonChild2);

        json.put(AmcConstants.APMessage.APPORTION_LIST, arr);
        System.out.println(json.toJSONString());
        
//        IMessageSender msgSender = MDSClientFactory
//                .getSenderClient(AmcConstants.MDSTopic.AP_TOPIC);
//
//        msgSender.send(json.toString(), new Random(1000).nextLong());
        log.info("----mds sender success");

	}
	/**
	 * 
	 * 销账MDS消息发送
	 * 
	 * @author LiangMeng
	 */
	@Test
	public void testWOMDS(){
        JSONObject json = new JSONObject();
        json.put(AmcConstants.APMessage.ACCT_ID, "1234");
        json.put(AmcConstants.APMessage.TENANT_ID, "TR");
        System.out.println(json.toJSONString());
//        IMessageSender msgSender = MDSClientFactory
//                .getSenderClient(AmcConstants.MDSTopic.WO_TOPIC);
//        msgSender.send(json.toString(), new Random(1000).nextLong());
        log.info("销账消息发送成功");

    }
}
