package com.ai.baas.amc.test.api.invoice.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.invoice.interfaces.IInvoiceInfoSV;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoDetailVO;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoParam;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoQueryResponse;
import com.ai.baas.amc.api.invoice.params.InvoiceQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordInsertParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryReponse;
import com.ai.opt.base.vo.BaseResponse;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class InvoiceInfoSVImplTest {

	@Autowired
	IInvoiceInfoSV iInvoiceInfoSV;
	
	@Test
	public void saveTest(){
		InvoiceInfoParam param = new InvoiceInfoParam();
		param.setCustId("17");
		param.setAcctId("17");
		param.setAddress("dasdasds");
		param.setEmail("asdadasda");
		param.setBankAcctNo("12321312");
		param.setBankName("sdadsad");
		param.setInvoiceType(1);
		param.setIssueType(0);
		param.setLinkName("asdsadad");
		param.setMobileNo("12313123");
		param.setPhoneNo("1212321312321");
		param.setPostCode("asdawdq");
		param.setRegAddress("q1dsdawdwda123d");
		param.setRegPhone("123213123");
		param.setTenantId("VIV-BYD");
		param.setTitle("测试标题2");
		param.setTaxRegNo("sadadada");
//		param.setInvoiceInfoId("1441");
		iInvoiceInfoSV.saveInvoiceInfo(param);
	}
	
	@Test
	public void queryTest(){
		InvoiceQueryParam queryParam = new InvoiceQueryParam();
		queryParam.setInvoiceInfoId("1441");
		queryParam.setAcctId("sadad");
		queryParam.setTenantId("asdasd");
		queryParam.setUserType("0");
		InvoiceInfoQueryResponse response = iInvoiceInfoSV.queryInvoiceInfo(queryParam);
		System.out.println(JSONObject.toJSONString(response.getInvoiceInfoVO()));
	}
	
	@Test
	public void queryDetailTest(){
		InvoiceRecordQueryParam queryParam = new InvoiceRecordQueryParam();
		queryParam.setUserType("1");
		queryParam.setTenantId("VIV-BYD");
		//queryParam.setAcctId("17");
		List<String> list = new ArrayList<String>(){{
            add("201602");
            add("201603");
            add("201604");
            add("201605");
            add("201606");
        }};
        queryParam.setBillMonth(list);
        List<Long> custIds = new ArrayList<Long>(){{
            add((long) 123);
        }};
        queryParam.setCustIds(custIds);
		queryParam.setPageNo(1);
		queryParam.setPageSize(10);
		InvoiceRecordQueryReponse response = iInvoiceInfoSV.queryInvoiceRecord(queryParam);
		System.out.println(JSONObject.toJSONString(response.getRecords()));
	}
	
	@Test
	public void queryInfoDetailTest(){
		InvoiceRecordQueryParam queryParam = new InvoiceRecordQueryParam();
		queryParam.setUserType("0");
		queryParam.setTenantId("VIV-BYD");
		//queryParam.setAcctId("17");
		List<InvoiceInfoDetailVO> response = iInvoiceInfoSV.queryInvoiceInfoDetailRecord(queryParam);
		System.out.println(JSONObject.toJSONString(response));
	}
	
	@Test
	public void saveInfoDetailTest(){
		//测试一下
		InvoiceRecordInsertParam queryParam = new InvoiceRecordInsertParam();
		queryParam.setAcctId("17");
		queryParam.setExpressNo("12233");
		queryParam.setSendTime(new Timestamp(new Date().getTime()));
		queryParam.setBillMonth("201609");
		queryParam.setCustId("17");
		queryParam.setTenantId("VIV-BYD");
		BaseResponse response = iInvoiceInfoSV.saveInvoiceRecord(queryParam);
		System.out.println(JSONObject.toJSONString(response));
	}
}
