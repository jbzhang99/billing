package com.ai.opt.sys.test.api.gn_subject.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectManageSV;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailViewResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedRequest;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class GnSubjectSVImplTest {
	private static final Log LOG = LogFactory.getLog(GnSubjectSVImplTest.class);
	
	@Autowired
	private IGnSubjectQuerySV gnSubjectQuerySV;
	
	@Autowired
	private IGnSubjectManageSV gnSubjectManageSV;
	//add info
	//@Test
	public void addGnSubject(){
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		gnSubjectInfoVo.setTenantId("baas-test");
		gnSubjectInfoVo.setSubjectType("11");
		gnSubjectInfoVo.setIndustryCode("01");
		gnSubjectInfoVo.setUnitName("分钟");
		gnSubjectInfoVo.setSubjectId(new Long(199));
		gnSubjectInfoVo.setSubjectName("测试类科目12333");
		gnSubjectInfoVo.setSubjectDesc("测试类科目描述信息");
		BaseResponse response = this.gnSubjectManageSV.addGnSubject(gnSubjectInfoVo);
		System.out.println("--------------param-------------:"+JSON.toJSONString(gnSubjectInfoVo));
		LOG.info("--------------########-------------:"+JSON.toJSONString(response));
	}
	//delete info
	//@Test
	public void delGnSubject(){
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		gnSubjectInfoVo.setTenantId("333");
		gnSubjectInfoVo.setIndustryCode("01");
		gnSubjectInfoVo.setSubjectId(new Long(133333));
		BaseResponse response = this.gnSubjectManageSV.delGnSubject(gnSubjectInfoVo);
		String json = JSON.toJSONString(response);
		
		LOG.info("--------------########-------------:"+json);
		LOG.info("--------------########-------------:删除成功");
	}
	//update info
	@Test
	public void updateGnSubject(){
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		gnSubjectInfoVo.setTenantId("baas-test");
		gnSubjectInfoVo.setIndustryCode("10");
		gnSubjectInfoVo.setSubjectId(new Long(251));
		//
		gnSubjectInfoVo.setSubjectName("科目账单test21");
		gnSubjectInfoVo.setSubjectDesc("zhangzhongde测试zzzzzz");
		//
		BaseResponse response = this.gnSubjectManageSV.updateGnSubject(gnSubjectInfoVo);
		LOG.info("--------------########-------------:"+JSON.toJSONString(response));
	}
	//pageSearch info
	@Test
	public void pageSearchGnSubject(){
		GnSubjectQueryVo gnSubjectQueryVo = new GnSubjectQueryVo();
		gnSubjectQueryVo.setPageNo(1);
		gnSubjectQueryVo.setPageSize(3);
		//gnSubjectQueryVo.setSubjectId(new Long(400008));
		//gnSubjectQueryVo.setSubjectName("加油包");
		//
		PageInfo<GnSubjectListResponse> gnSubjectListResponsePageInfo = this.gnSubjectQuerySV.getGnSubjectList(gnSubjectQueryVo);
		//
		String resultJsonStr = JSON.toJSONString(gnSubjectListResponsePageInfo);
		System.out.println("--------------param-------------:"+JSON.toJSONString(gnSubjectQueryVo));
		System.out.println("--------------########-------------:"+gnSubjectListResponsePageInfo.getCount());
		System.out.println("--------------########-------------:JsonStr-->>"+resultJsonStr);
	}
	//find by key unique info
	//@Test
	public void getGnSubject(){
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		gnSubjectInfoVo.setTenantId("444");
		gnSubjectInfoVo.setSubjectId(new Long(133333));
		gnSubjectInfoVo.setIndustryCode("01");
		//
		GnSubjectInfoVo gnSubjectInfoVoNew = this.gnSubjectQuerySV.getGnSubject(gnSubjectInfoVo);
		//json string
		String gnSubjectInfoVoJsonStr = JSON.toJSONString(gnSubjectInfoVoNew);
		LOG.info("--------------########-------------:查询单条信息param--->>>"+JSON.toJSONString(gnSubjectInfoVo));
		LOG.info("--------------########-------------:查询单条信息--->>>"+gnSubjectInfoVoJsonStr);
	}
	
	//@Test
	public void getGnSubjectListMayRelated(){
		GnSubjectDetailVo gnSubjectDetailVo = new GnSubjectDetailVo();
		gnSubjectDetailVo.setTenantId("0");
		gnSubjectDetailVo.setIndustryCode("02");
		gnSubjectDetailVo.setSubjectType("11");
		//
		List<GnSubjectListResponse> gnSubjectDetailVoNew = this.gnSubjectQuerySV.getGnSubjectListMayRelated(gnSubjectDetailVo);
		//json string
		String gnSubjectInfoVoJsonStr = JSON.toJSONString(gnSubjectDetailVoNew);
		LOG.info("--------------########-------------:param--->>>"+JSON.toJSONString(gnSubjectDetailVo));
		LOG.info("--------------########-------------:可关联的详单信息--->>>"+gnSubjectInfoVoJsonStr);
	}
	//@Test
	public void getGnSubjectListRelated(){
		GnSubjectRelatedDetailVo gnSubjectRelatedDetailVo = new GnSubjectRelatedDetailVo();
		gnSubjectRelatedDetailVo.setTenantId("33311");
		List<String> strList = new ArrayList<String>();
		strList.add("31");
		strList.add("41");
		strList.add("51");
		strList.add("61");
		gnSubjectRelatedDetailVo.setDrSubjectId(strList);
		//
		List<GnSubjectListResponse> gnSubjectDetailListNew = this.gnSubjectQuerySV.getGnSubjectListRelated(gnSubjectRelatedDetailVo);
		//json string
		String gnSubjectInfoVoJsonStr = JSON.toJSONString(gnSubjectDetailListNew);
		LOG.info("--------------########-------------:param--->>>"+JSON.toJSONString(gnSubjectRelatedDetailVo));
		LOG.info("--------------########-------------:已关联的详单信息--->>>"+gnSubjectInfoVoJsonStr);
	}
	//@Test
	public void getGnSubjectByTenantIdSubjectId(){
		GnSubjectTenantIdSubjectIdRequest request = new GnSubjectTenantIdSubjectIdRequest();
		request.setSubjectId("171");
		request.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
		GnSubjectInfoVo gnSubjectInfoVo = this.gnSubjectQuerySV.getGnSubjectByTenantIdSubjectId(request);
		LOG.info("--------------########-------------:result--->>>"+JSON.toJSONString(gnSubjectInfoVo));
	}
	@Test
	public void queryRelatedGnSubject(){
		GnSubjectRelatedRequest request = new GnSubjectRelatedRequest();
		request.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
		request.setIndustryCode("1");
		request.setSubjectId("133");
		//
		LOG.info("--------------########-------------:param--->>>"+JSON.toJSONString(request));
		GnSubjectRelatedDetailViewResponse response = this.gnSubjectQuerySV.queryRelatedGnSubject(request);
		LOG.info("--------------########-------------:result--->>>"+JSON.toJSONString(response));
	}
}
