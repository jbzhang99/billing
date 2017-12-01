package com.ai.baas.dst.api.discountcal.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 优惠活动计算服务返回结果
 * @author wangjing19
 *
 */
public class DiscountCalResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 原价
     */
    private String OriginalPrice;
    
    /**
     * 优惠额度
     */
    private String discountPrice;
    
    /**
     * 优惠描述
     */
    private String discountDesc;
    
    /**
     * 类目ID
     */
    private String categoryID;
    
    /**
     * 活动ID
     */
    private String discountID;

	public String getOriginalPrice() {
		return OriginalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		OriginalPrice = originalPrice;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getDiscountDesc() {
		return discountDesc;
	}

	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}

}
