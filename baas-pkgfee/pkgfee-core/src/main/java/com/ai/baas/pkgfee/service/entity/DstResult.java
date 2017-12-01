package com.ai.baas.pkgfee.service.entity;

import java.math.BigDecimal;

public class DstResult {
	private BigDecimal dstAmount; // 优惠金额
	private String dstSubjectCode;// 优惠科目

	public BigDecimal getDstAmount() {
		return dstAmount;
	}

	public void setDstAmount(BigDecimal dstAmount) {
		this.dstAmount = dstAmount;
	}

	public String getDstSubjectCode() {
		return dstSubjectCode;
	}

	public void setDstSubjectCode(String dstSubjectCode) {
		this.dstSubjectCode = dstSubjectCode;
	}

}
