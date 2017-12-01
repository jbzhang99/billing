package com.ai.baas.prd.api.element.params;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable{
    private static final long serialVersionUID = 1L;
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
     * 子产品名称
     */
    private String categoryName;
    /*
     * 计费周期
     */
    private String billingCycle;
    /*
     * 行业类型编码
     */
    private String tradeCode;
    private List<Element>  elements;
    
    public String getTradeCode() {
        return tradeCode;
    }
    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }
    public String getBillingCycle() {
        return billingCycle;
    }
    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    public List<Element> getElements() {
        return elements;
    }
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
