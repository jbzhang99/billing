package com.ai.baas.dst.api.billsdiscount.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单优惠活动产品列表查询返回结果
 * @author wangluyang
 *
 */
public class BillDiscountListResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     * 账单优惠活动产品列表信息
     */
    private List<BillDiscountInfo> discounts;

	public List<BillDiscountInfo> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<BillDiscountInfo> discounts) {
		this.discounts = discounts;
	}
}
