package com.ai.baas.prd.api.product.params;

import java.io.Serializable;
import java.util.List;

public class DimensionRes implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dimensionName;
	private String dimensionCode;
	private String dimensionId;
	private String dimensionSeq;
	private List<BranchRes> branchs;
	public String getDimensionName() {
		return dimensionName;
	}
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	public String getDimensionCode() {
		return dimensionCode;
	}
	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}
	public String getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}
	public List<BranchRes> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<BranchRes> branchs) {
		this.branchs = branchs;
	}
	public String getDimensionSeq() {
		return dimensionSeq;
	}
	public void setDimensionSeq(String dimensionSeq) {
		this.dimensionSeq = dimensionSeq;
	}
	
}
