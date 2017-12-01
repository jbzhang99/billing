package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;
import java.util.List;

public class PriceFactorVO implements Serializable {

	private static final long serialVersionUID = 1911990485442253637L;
	private String categoryId;
	private String categoryName;
	private List<MemberVO> memberList;
	private List<SpecificationVO> specList;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<MemberVO> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<MemberVO> memberList) {
		this.memberList = memberList;
	}

	public List<SpecificationVO> getSpecList() {
		return specList;
	}

	public void setSpecList(List<SpecificationVO> specList) {
		this.specList = specList;
	}
	
}
