package com.ai.baas.prd.api.product.params;

import java.io.Serializable;

public class BranchRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String branchName;
	private String branchCode;
	private String branchId;
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	
}
