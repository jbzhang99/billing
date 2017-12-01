package com.ai.prd.test;

import com.ai.baas.prd.api.category.interfaces.ICategorySV;
import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.category.params.CategoryQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CategorySVTest {

    @Autowired
    private ICategorySV categorySV;

    @Test
    public void getCategoryList(){
        CategoryQuery query = new CategoryQuery();
        query.setTenantId("ECITIC");
        query.setCategoryType("ZJ");
        query.setCategoryLevel("1");
        CategoryQueryResponse categoryList = categorySV.getCategoryList(query);
    }

}
