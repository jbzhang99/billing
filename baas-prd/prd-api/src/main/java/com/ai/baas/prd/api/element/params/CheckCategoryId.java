package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;

public class CheckCategoryId extends BaseInfo {
    private static final long serialVersionUID = 1L;
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
}
