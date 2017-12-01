package com.ai.baas.prd.api.product.params;

import java.io.Serializable;

public class BranchVo implements Serializable{

	
	private static final long serialVersionUID = 1L;

	/**
	 * 分支名称
	 */
	private String branchName;
	/**
	 * 分支编码
	 */
	private String branchCode;

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
	
	
}
