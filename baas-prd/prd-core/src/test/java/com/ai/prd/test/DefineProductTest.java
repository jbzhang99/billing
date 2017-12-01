package com.ai.prd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.prd.api.product.params.DelProductReq;
import com.ai.baas.prd.api.product.params.ProductRequest;
import com.ai.baas.prd.api.product.params.ProductUpdateReq;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.baas.prd.service.business.interfaces.IProductDefineBusiSV;
import com.ai.baas.prd.util.ReadJsonUtil;
import com.alibaba.fastjson.JSON;

/**
 * 定义产品目录测试类
 *
 * Date: 2016年11月16日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DefineProductTest {
	@Autowired
	IProductDefineBusiSV iProductDefineSV;
	@Test
	public void testDelProduct(){//删除某个主产品及其子产品
		DelProductReq req=new DelProductReq();
		req.setMainProCode("cloud");
		req.setTenantId("ECTIC");
		System.out.println(JSON.toJSONString(iProductDefineSV.delProduct(req)));
		
	}
	@Test
	public void addProduct(){
		String json=new ReadJsonUtil().ReadFile("C:\\Users\\scorpion\\Desktop\\dim.json");
		ProductRequest req=JSON.parseObject(json, ProductRequest.class);
		iProductDefineSV.addProduct(req);
	}
	@Test
	public void testUpdate(){
		String json=new ReadJsonUtil().ReadFile("C:\\Users\\scorpion\\Desktop\\dim1.json");
		ProductUpdateReq req=JSON.parseObject(json,ProductUpdateReq.class);
		req.setTenantId("ECITIC");
		System.out.println("------->"+iProductDefineSV.updatePmCategoryInfo(req));
	}
	@Test
	public void getProducts(){
		QueryPmCategoryInfoReq req=new QueryPmCategoryInfoReq();
	    
    	req.setPageNO(1);
    	req.setPageSize(10);
    	req.setTenantId("ECTIC");
    
    	System.out.println(JSON.toJSONString(iProductDefineSV.getCategoryInfos(req)));
	}
}
