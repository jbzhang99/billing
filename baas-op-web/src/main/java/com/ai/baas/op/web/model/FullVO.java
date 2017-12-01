package com.ai.baas.op.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;

public class FullVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long presentId;
	  private String giftType;
	  private double gitfAmount;
	  private Timestamp giftActiveDate;
	  private Timestamp giftInvalidDate;
	  private List<ProductInfo> giftProList;
	  private String activeCycle;
	  private String activeFlag;
	public Long getPresentId() {
		return presentId;
	}
	public void setPresentId(Long presentId) {
		this.presentId = presentId;
	}
	public String getGiftType() {
		return giftType;
	}
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	public double getGitfAmount() {
		return gitfAmount;
	}
	public void setGitfAmount(double gitfAmount) {
		this.gitfAmount = gitfAmount;
	}
	public Timestamp getGiftActiveDate() {
		return new Timestamp(giftActiveDate.getTime());
	}
	public void setGiftActiveDate(Timestamp giftActiveDate) {
		this.giftActiveDate = new Timestamp(giftActiveDate.getTime());
	}
	public Timestamp getGiftInvalidDate() {
		return new Timestamp(giftInvalidDate.getTime());
	}
	public void setGiftInvalidDate(Timestamp giftInvalidDate) {
		this.giftInvalidDate = new Timestamp(giftInvalidDate.getTime());
	}
	
	public List<ProductInfo> getGiftProList() {
		return giftProList;
	}
	public void setGiftProList(List<ProductInfo> giftProList) {
		this.giftProList = giftProList;
	}
	public String getActiveCycle() {
		return activeCycle;
	}
	public void setActiveCycle(String activeCycle) {
		this.activeCycle = activeCycle;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	  
}
