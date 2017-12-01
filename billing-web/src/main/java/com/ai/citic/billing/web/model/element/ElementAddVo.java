package com.ai.citic.billing.web.model.element;

public class ElementAddVo {
	
	/*
    * 行业类型编码
    */
    private String tradeCode;
	 /*
     * 主产品ID
     */
    private String mainProductId;
       /*
     * 主产品名称
     */
    private String mainProductName;
       /*
     * 类目ID
     */
    private String categoryId;
    /*
     * 计费周期
     */
    private String billingCycle;
    
    /**
     * 计费模式
     */
    private String billingMode;
    /**
     * 计费模式编码
     */
    private String modeCode;
    
    private String elementListStr;
    
	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getMainProductId() {
		return mainProductId;
	}

	public void setMainProductId(String mainProductId) {
		this.mainProductId = mainProductId;
	}

	public String getMainProductName() {
		return mainProductName;
	}

	public void setMainProductName(String mainProductName) {
		this.mainProductName = mainProductName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getBillingMode() {
		return billingMode;
	}

	public void setBillingMode(String billingMode) {
		this.billingMode = billingMode;
	}

	public String getModeCode() {
		return modeCode;
	}

	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}

	public String getElementListStr() {
		return elementListStr;
	}

	public void setElementListStr(String elementListStr) {
		this.elementListStr = elementListStr;
	}
}
