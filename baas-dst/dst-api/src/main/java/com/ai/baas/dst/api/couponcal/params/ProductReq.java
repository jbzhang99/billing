package com.ai.baas.dst.api.couponcal.params;

import java.io.Serializable;
/**
 * 产品列表详情
 *
 * Date: 2017年3月20日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author wangjing19
 */
public class ProductReq implements Serializable {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 单价
	 */
	private String unitPrice;
	
	/**
	 * 数量
	 */
	private String quantity;
	
	/**
	 * ID
	 */
	private String productID;
	
	/**
	 * 折扣价格
	 */
	private String discountAmount;
	
	/**
	 * 原始价格
	 */
	private String originalAmount;
	
	/**
	 * 现总价
	 */
	private String curAmount;

	/**
	 * 时长
	 */
	private String timeDuration;
	
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getCurAmount() {
		return curAmount;
	}

	public void setCurAmount(String curAmount) {
		this.curAmount = curAmount;
	}

	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}

	
}
