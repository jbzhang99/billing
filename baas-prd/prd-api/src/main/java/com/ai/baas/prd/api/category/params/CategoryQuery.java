package com.ai.baas.prd.api.category.params;

import com.ai.opt.base.vo.BaseInfo;

public class CategoryQuery extends BaseInfo{
    private String categoryType;
    private String categoryLevel;
    private String parentId;

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
