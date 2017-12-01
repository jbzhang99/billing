package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;

public class MemberVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String branch;
	private String dimension;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
}
