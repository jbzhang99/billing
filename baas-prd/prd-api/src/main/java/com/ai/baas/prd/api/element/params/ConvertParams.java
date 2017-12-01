package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;

public class ConvertParams extends BaseInfo{
    /*
     * 类目ID
     */
    private String categoryId;

    private String categoryType;
    
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }


}
