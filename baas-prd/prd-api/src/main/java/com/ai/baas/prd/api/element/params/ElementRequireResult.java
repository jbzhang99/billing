package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class ElementRequireResult extends BaseResponse{

    private static final long serialVersionUID = 1L;
    private String tenantId;
    private PageInfo<Product> products;
    public PageInfo<Product> getProducts() {
        return products;
    }
    public void setProducts(PageInfo<Product> products) {
        this.products = products;
    }
    public String getTenantId() {
        return tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    

}
