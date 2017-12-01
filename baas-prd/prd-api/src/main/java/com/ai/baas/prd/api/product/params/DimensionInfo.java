package com.ai.baas.prd.api.product.params;

import java.io.Serializable;
import java.util.List;

public class DimensionInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 维度名称
	 */
	private String dimensionName;
	/**
	 * 维度编码
	 */
	private  String dimensionCode;
	/**
	 * 维度顺序
	 */
	private String dimensionSeq;
	/**
	 * 维度id
	 */
	private String dimensionId;
	/**
	 * 分支
	 */
	private List<BranchInfo> branchs;
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
	public String getDimensionSeq() {
		return dimensionSeq;
	}
	public void setDimensionSeq(String dimensionSeq) {
		this.dimensionSeq = dimensionSeq;
	}
	public List<BranchInfo> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<BranchInfo> branchs) {
		this.branchs = branchs;
	}
	public String getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}
	
	
	
}
