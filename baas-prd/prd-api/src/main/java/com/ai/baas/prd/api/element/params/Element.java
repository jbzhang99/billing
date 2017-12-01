package com.ai.baas.prd.api.element.params;

import java.io.Serializable;

public class Element implements Serializable{
    private static final long serialVersionUID = 1L;
    /*
     * 计费周期
     */
    private String billingCycle;
    /*
     * 规格类别ID
     */
    private String specTypeId;
    /*
     * 规格类别名称
     */
    private String specTypeName;
    /*
     * 定价策略ID
     */
    private String pricePolicy;
    /*
     * 定价策略名称
     */
    private String pricePolicyName;
    
    
    public String getPricePolicyName() {
        return pricePolicyName;
    }
    public void setPricePolicyName(String pricePolicyName) {
        this.pricePolicyName = pricePolicyName;
    }
    public String getBillingCycle() {
        return billingCycle;
    }
    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }
    public String getSpecTypeId() {
        return specTypeId;
    }
    public void setSpecTypeId(String specTypeId) {
        this.specTypeId = specTypeId;
    }
    public String getSpecTypeName() {
        return specTypeName;
    }
    public void setSpecTypeName(String specTypeName) {
        this.specTypeName = specTypeName;
    }
    public String getPricePolicy() {
        return pricePolicy;
    }
    public void setPricePolicy(String pricePolicy) {
        this.pricePolicy = pricePolicy;
    }
    
}
