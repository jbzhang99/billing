package com.ai.baas.prd.api.convert.params;

import com.ai.opt.base.vo.BaseInfo;

public class ConvertParams extends BaseInfo{
    /*
     * 类目ID
     */
    private String CategoryId;

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
}
