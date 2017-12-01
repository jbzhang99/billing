package com.ai.baas.pkgfee.api.feecal.params;

import java.io.Serializable;
import java.util.List;

public class FeeCalAddParam implements Serializable {

	private static final long serialVersionUID = 1272990931303793964L;
	private String orderId;
	private String tenantId;
	private String custId;
	private String subsId;
	private String acctId;
	//private String orderDiscountId; //订单优惠ID
	private String couponId;
	private String deductMode;
	private List<ProductItemVO> productList;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getSubsId() {
		return subsId;
	}

	public void setSubsId(String subsId) {
		this.subsId = subsId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getDeductMode() {
		return deductMode;
	}

	public void setDeductMode(String deductMode) {
		this.deductMode = deductMode;
	}

	public List<ProductItemVO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductItemVO> productList) {
		this.productList = productList;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

}
