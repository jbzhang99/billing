package com.ai.baas.dst.api.billsdiscount.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 删除指定账单优惠活动产品请求
 * @author wangluyang
 *
 */
public class BillDiscountDeleteRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
        
    /**
     * 账单优惠活动ID
     */
    private String discountId;

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	
}
