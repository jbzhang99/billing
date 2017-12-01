package com.ai.baas.pkgfee.api.feecal.params;

import java.io.Serializable;

public class ProductItemVO implements Serializable {

	private static final long serialVersionUID = -721144275247372485L;
	private String productId; //订购通知接口中传过来的产品ID
	private String productName;
	private String activeTime;
	private String inactiveTime;
	private String productPrice;
	private String productMode;
	private String purchaseNum;
	private String purchaseUnit;
	private String subjectCode;
	private String discountId;
	private String dstActiveTime;
	private String dstInactiveTime;
	private String autoRenew;
	private String factorCode;
	private String instanceId;
	private String priceCode;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(String inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductMode() {
		return productMode;
	}

	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public String getDstActiveTime() {
		return dstActiveTime;
	}

	public void setDstActiveTime(String dstActiveTime) {
		this.dstActiveTime = dstActiveTime;
	}

	public String getDstInactiveTime() {
		return dstInactiveTime;
	}

	public void setDstInactiveTime(String dstInactiveTime) {
		this.dstInactiveTime = dstInactiveTime;
	}

	public String getAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(String autoRenew) {
		this.autoRenew = autoRenew;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	
}
