package com.ai.prd.test;

import java.util.*;

import com.ai.baas.prd.api.category.interfaces.IDiscountPriceProCategorySV;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryRequest;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryResponse;
import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DisProductCategorySVTest {

    @Autowired
    private IDiscountPriceProCategorySV iDisProductsCategorySV;

    @Test
    public void getCategoryList(){
    	DiscountPriceProCategoryRequest request = new DiscountPriceProCategoryRequest();
    	
    	List<String> productIDs = new ArrayList<String>();
//    	productIDs.add("81");
//    	productIDs.add("82");
//    	productIDs.add("83");
    	productIDs.add("404");
    	productIDs.add("405");
    	productIDs.add("406");
    	
    	request.setPriceProductIDs(productIDs);
    	request.setTenantId("WOCLOUD");
    	
    	System.out.println(JSON.toJSONString(request));
    	
//        DisProsCategoryResponse categoryList = iDisProductsCategorySV.getDisProsCategoryID(request);
//        System.out.println("DisProsCategoryID:" + categoryList.getDisProsCategoryID());
    }

}
