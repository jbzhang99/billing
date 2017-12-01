package com.ai.baas.bmc.srv.entity;

import java.math.BigDecimal;

/**
 * 科目和批价信息
 * @author majun
 *
 */
public class SubjectAndPrice {
	private String subjectCode;
	private BigDecimal price;
	private String priceCode;
	
	public SubjectAndPrice(String subjectCode, BigDecimal price) {
		this.subjectCode = subjectCode;
		this.price = price;
	}
	
	public SubjectAndPrice(String subjectCode, BigDecimal price, String priceCode) {
		this.subjectCode = subjectCode;
		this.price = price;
		this.priceCode = priceCode;
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

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	
	
	
}
