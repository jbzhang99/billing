package com.ai.baas.bmc.srv.entity;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 计费批价结果
 * @author majun
 *
 */
public class BillingPriceResult {
	private List<SubjectAndPrice> subjectAndPrices = Lists.newArrayList();
	private String remainUsageAmount;  //剩余使用量
	private boolean isCompleted = true;//是否批价完成
	
	public void addSubjectAndPrices(SubjectAndPrice subjectAndPrice){
		subjectAndPrices.add(subjectAndPrice);
	}

	public List<SubjectAndPrice> getSubjectAndPrices() {
		return subjectAndPrices;
	}

	public void setSubjectAndPrices(List<SubjectAndPrice> subjectAndPrices) {
		this.subjectAndPrices = subjectAndPrices;
	}

	public String getRemainUsageAmount() {
		return remainUsageAmount;
	}

	public void setRemainUsageAmount(String remainUsageAmount) {
		this.remainUsageAmount = remainUsageAmount;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
