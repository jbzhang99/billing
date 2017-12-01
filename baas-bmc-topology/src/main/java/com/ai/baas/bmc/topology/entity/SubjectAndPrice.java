package com.ai.baas.bmc.topology.entity;

import java.math.BigDecimal;

/**
 * 科目和批价信息
 * @author majun
 *
 */
public class SubjectAndPrice {
	private String subjectCode;
	private BigDecimal price;
	//private boolean isCalSucc = false;//是否批价成功

	public SubjectAndPrice(String subjectCode, BigDecimal price) {
		this.subjectCode = subjectCode;
		this.price = price;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
