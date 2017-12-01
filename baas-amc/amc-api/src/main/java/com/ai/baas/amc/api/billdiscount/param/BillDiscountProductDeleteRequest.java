package com.ai.baas.amc.api.billdiscount.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 删除指定账单优惠活动产品请求
 *
 * Date: 2016年4月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductDeleteRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
        
    /**
     * 账单优惠产品ID
     */
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
