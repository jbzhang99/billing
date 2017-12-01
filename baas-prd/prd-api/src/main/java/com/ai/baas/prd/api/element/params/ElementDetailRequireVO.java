package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;
/**
 * 子产品定价元素查询vo
 * @author wangkai16
 *
 */
public class ElementDetailRequireVO extends BaseInfo{

    /*
     * 类目ID
     */
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    
}
