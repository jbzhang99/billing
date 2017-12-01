package com.ai.baas.pkgfee.service.entity;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;

public class PkgFeeFundFreeze {
	private String tenantId;
	private String deductMode;
	private String custId;
	private String instanceId;
	private Long amount;//冻结金额=产品总价-优惠金额   单位:里
	private String productMode;
	private CpPackageFee cpPackageFee;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDeductMode() {
		return deductMode;
	}

	public void setDeductMode(String deductMode) {
		this.deductMode = deductMode;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public CpPackageFee getCpPackageFee() {
		return cpPackageFee;
	}

	public void setCpPackageFee(CpPackageFee cpPackageFee) {
		this.cpPackageFee = cpPackageFee;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getProductMode() {
		return productMode;
	}

	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}

}
