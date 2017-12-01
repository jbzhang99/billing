package com.ai.baas.prd.api.product.params;

import java.io.Serializable;
import java.util.List;

public class DimensionVO implements Serializable{

	
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
	 * 分支
	 */
	private List<BranchVo> branchs;
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
	public List<BranchVo> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<BranchVo> branchs) {
		this.branchs = branchs;
	}
	
	
}
