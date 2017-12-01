package com.ai.baas.prd.api.category.params;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

public class CategoryQueryResponse extends BaseResponse{
    private List<CategoryVo> categoryList;

    public List<CategoryVo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryVo> categoryList) {
        this.categoryList = categoryList;
    }
}
