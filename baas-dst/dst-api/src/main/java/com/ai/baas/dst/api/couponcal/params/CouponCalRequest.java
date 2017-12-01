package com.ai.baas.dst.api.couponcal.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 优惠卷计算服务请求参数
 * @author wangjing19
 *
 */
public class CouponCalRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
      
    /**
     * 产品列表
     */
    private List<ProductReq> products;
    
    /**
     * 优惠卷码
     */
    private String couponCode;
    
	public List<ProductReq> getProducts() {
		return products;
	}

	public void setProducts(List<ProductReq> products) {
		this.products = products;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

}
