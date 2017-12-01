package com.ai.baas.op.web.model;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountInfo;

public class BillDiscountInfoVo extends BillDiscountInfo{
    
    private static final long serialVersionUID = 1258575444456556924L;
    
    private String discountTypeDesc;
    
    /**
     * 优惠活动规则
     */
    private String discountRuleStr;

    public String getDiscountTypeDesc() {
        return discountTypeDesc;
    }

    public void setDiscountTypeDesc(String discountTypeDesc) {
        this.discountTypeDesc = discountTypeDesc;
    }

	public String getDiscountRuleStr() {
		return discountRuleStr;
	}

	public void setDiscountRuleStr(String discountRuleStr) {
		this.discountRuleStr = discountRuleStr;
	}

}
