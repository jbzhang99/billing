package com.ai.baas.op.web.model;

import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductInfo;

public class BillDiscountProductInfoVo extends BillDiscountProductInfo{
    
    private static final long serialVersionUID = 1258575444456556924L;
    
    private String discountTypeDesc;

    public String getDiscountTypeDesc() {
        return discountTypeDesc;
    }

    public void setDiscountTypeDesc(String discountTypeDesc) {
        this.discountTypeDesc = discountTypeDesc;
    }

}
