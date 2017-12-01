package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;

public class UpdateByProductVo extends BaseInfo{

    /*
     * 主产品ID
     */
    private String CategoryId;

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
    
}
