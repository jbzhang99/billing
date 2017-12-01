package com.ai.baas.prd.api.element.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class ElementDeleteVO extends BaseInfo{

    private static final long serialVersionUID = 1L;
    /*
     * 主产品ID
     */
    private String mainProductId;
    
    private List<String> categoryIds;
    
       /*
     * 类目ID
     */
    private String categoryId;
    
     

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMainProductId() {
        return mainProductId;
    }

    public void setMainProductId(String mainProductId) {
        this.mainProductId = mainProductId;
    }
    
}
