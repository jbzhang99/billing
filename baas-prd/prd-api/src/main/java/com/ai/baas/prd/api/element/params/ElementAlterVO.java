package com.ai.baas.prd.api.element.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 定价元素新增vo
 * @author wangkai16
 *
 */
public class ElementAlterVO extends BaseInfo{

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
     * 计费周期
     */
    private String billingCycle;
    /*
    * 行业类型编码
    */
    private String tradeCode;
    /*
    * 元素
    */
    List<Element> elements;
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
    public List<Element> getElements() {
        return elements;
    }
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
    public String getBillingCycle() {
        return billingCycle;
    }
    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

   
}
