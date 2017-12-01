package com.ai.baas.dst.api.billsdiscount.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠维护返回结果
 * @author wangluyang
 *
 */
public class BillDiscountMaintainResponse extends BaseResponse {

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
     * 优惠活动ID 
     */
    private String discountId;

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

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
    
}
