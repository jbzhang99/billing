package com.ai.baas.prd.service.business.interfaces;

import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.category.params.CategoryVo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;

import java.util.List;

public interface ICategoryBusiSV {
    List<PmCategoryInfo> getCategoryList(CategoryQuery req);
}
