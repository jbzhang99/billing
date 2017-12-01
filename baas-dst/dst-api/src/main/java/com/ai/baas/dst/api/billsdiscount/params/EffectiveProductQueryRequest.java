package com.ai.baas.dst.api.billsdiscount.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 生效商品查询
 * @author wangluyang
 *
 */
public class EffectiveProductQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
       
    /**
     * 生效日期
     */
    private String effectDate;

    /**
     * 失效日期
     */
    private String expireDate;
    
    /**
     * 不需要处理的的优惠活动id
     */
    private List<String> discountIds;

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public List<String> getDiscountIds() {
		return discountIds;
	}

	public void setDiscountIds(List<String> discountIds) {
		this.discountIds = discountIds;
	}
}
