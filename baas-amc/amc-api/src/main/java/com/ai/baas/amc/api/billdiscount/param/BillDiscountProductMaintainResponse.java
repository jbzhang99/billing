package com.ai.baas.amc.api.billdiscount.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠维护返回结果
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductMaintainResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     */
    private String tradeSeq;
    
    /**
     * 租户ID 
     */
    private String tenantId;
    
    /**
     * 优惠产品ID 
     */
    private String productId;

    public String getTradeSeq() {
        return tradeSeq;
    }

    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
}
