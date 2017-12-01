package com.ai.opt.baas.bmc.test.api.custInfo.impl;

import com.ai.baas.cust.api.custinfo.interfaces.ICustInfoSV;
import com.ai.baas.cust.api.custinfo.params.CustIdParams;
import com.ai.baas.cust.api.custinfo.params.CustInfoParams;
import com.ai.baas.cust.api.custinfo.params.ExtInfo;
import com.ai.baas.cust.constants.CustState;
import com.ai.baas.cust.util.DshmUtil;
import com.ai.baas.cust.util.MyJsonUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CustInfoSVImplTest {

	@Autowired
	private ICustInfoSV custInfoSv;
	private static final Log log = LogFactory.getLog(CustInfoSVImplTest.class);

	@Test
	public void testCustNotify(){
		CustInfoParams custInfo = new CustInfoParams();
		custInfo.setCityCode("010");
		custInfo.setContactNo("111");
		custInfo.setCustAddress("北京");
		custInfo.setCustGrade("A");
		custInfo.setCustName("测试返回结果");
		custInfo.setCustType("P");
		custInfo.setEmail("xijinping@asiainfo.com");
		custInfo.setExtCustId("5544");
		custInfo.setProvinceCode("010");
		custInfo.setRemark("高级客户11111111");
		custInfo.setState(CustState.NORMAL.name());
		//custInfo.setStateChgTime("20160415172529");
		custInfo.setTenantId("WOCLOUD");
		custInfo.setTradeSeq("800115");
		//
		List<ExtInfo> extInfoList = new ArrayList<ExtInfo>();
		ExtInfo extInfo = new ExtInfo();
		extInfo.setExtName("test");
		extInfo.setExtValue("2223211a");
		extInfo.setUpdateFlag("N");
		extInfoList.add(extInfo);
		
		custInfo.setExtInfoList(extInfoList);
		log.info("-----------json param:"+JSON.toJSONString(custInfo));
		final BaseResponse res = this.custInfoSv.custNotify(custInfo);
		log.info("-----------json str:"+JSON.toJSONString(res));
		
		
		Map<String, String> params = new TreeMap<String, String>();
        params.put("EXT_CUST_ID", custInfo.getExtCustId());
        params.put("TENANT_ID", custInfo.getTenantId());
        
        List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo").where(params)
                .executeQuery(DshmUtil.getCacheClient());
        log.info("----------result size:"+result.size());
        log.info("----------result json:"+MyJsonUtil.toJson(result));
	}

	@Test
	public void testUpdateCustInfo(){
		CustInfoParams custInfo = new CustInfoParams();
		/*custInfo.setCityCode("010");
		custInfo.setContactNo("111");
		custInfo.setCustAddress("北京");
		custInfo.setCustGrade("A");*/
		custInfo.setCustName("樊樊");
		custInfo.setCustType("y");
		/*custInfo.setCustType("P");
		custInfo.setEmail("xijinping@asiainfo.com");
		custInfo.setProvinceCode("010");
		custInfo.setRemark("高级客户11111111");
		custInfo.setState(CustState.NORMAL.name());
		custInfo.setStateChgTime("20160415172529");*/
		custInfo.setExtCustId("1");
		custInfo.setTenantId("WOCLOUD");
		custInfo.setTradeSeq("800115");
		//
		List<ExtInfo> extInfoList = new ArrayList<ExtInfo>();
		ExtInfo extInfo = new ExtInfo();
		extInfo.setExtName("test");
		extInfo.setExtValue("455434fff");
		extInfo.setUpdateFlag("U");
		extInfoList.add(extInfo);

		custInfo.setExtInfoList(extInfoList);
		log.info("-----------json param:"+JSON.toJSONString(custInfo));
		BaseResponse baseResponse = this.custInfoSv.updateCustInfo(custInfo);
		log.info("-----------json str:"+JSON.toJSONString(baseResponse));


		Map<String, String> params = new TreeMap<String, String>();
		params.put("EXT_CUST_ID", custInfo.getExtCustId());
		params.put("TENANT_ID", custInfo.getTenantId());

		List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo").where(params)
				.executeQuery(DshmUtil.getCacheClient());
		log.info("----------result size:"+result.size());
		log.info("----------result json:"+MyJsonUtil.toJson(result));
	}

	@Test
	public void testDeleteCustInfo(){
		CustIdParams custIdParams = new CustIdParams();
		custIdParams.setExtCustId("ext_cust_id");
		custIdParams.setTenantId("ECITIC");
		log.info("-----------json param:"+JSON.toJSONString(custIdParams));
		BaseResponse baseResponse = this.custInfoSv.deleteCustInfo(custIdParams);
		log.info("-----------json str:"+JSON.toJSONString(baseResponse));

		Map<String, String> params = new TreeMap<String, String>();
		params.put("EXT_CUST_ID", custIdParams.getExtCustId());
		params.put("TENANT_ID", custIdParams.getTenantId());

		List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo").where(params)
				.executeQuery(DshmUtil.getCacheClient());
		log.info("----------result size:"+result.size());
		log.info("----------result json:"+MyJsonUtil.toJson(result));
	}
	//@Test
	public void testDelCustInfoDhms(){
		Map<String, String> params = new TreeMap<String, String>();
        params.put("EXT_CUST_ID", "1111");
        params.put("TENANT_ID", "11111");
        DshmUtil.getIdshmSV().initdel("bl_custinfo",MyJsonUtil.toJson(params));
        
        List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo").where(params)
                .executeQuery(DshmUtil.getCacheClient());
        log.info("----------result size:"+result.size());
        log.info("----------result json:"+MyJsonUtil.toJson(result));
	}
	@Test
	public void testCustInfoExt(){
		Map<String, String> params = new TreeMap<String, String>();
        params.put("CUST_ID", "25");
        params.put("EXT_NAME", "1115");
        
        List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo_ext").where(params)
                .executeQuery(DshmUtil.getCacheClient());
        log.info("----------result size:"+result.size());
        log.info("----------result json:"+MyJsonUtil.toJson(result));
	}
}
