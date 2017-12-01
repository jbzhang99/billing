package com.ai.baas.amc.api.billdiscount.param;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单优惠产品变更入参
 * 
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author fanpw
 */
public class BillDiscountProductUpdateVo extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 账单优惠产品ID
     */
    private String productId;

    /**
     * 优惠活动名称
     */
    private String productName;

    /**
     * 产品生效日期
     */
    private String effectDate;

    /**
     * 产品失效日期
     */
    private String expireDate;
    
    /**
     * 状态: <br>
     * 0:失效<br>
     * 1:生效<br>
     * 2:待生效<br>
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 满赠（满减）到达金额，单位为元<br>
     * 优惠类型为满赠、满减时使用<br>
     * 
     */
    private Double fullCostAmount;

    /**
     * 扣减金额，单位为元<br>
     * 优惠类型为满减优惠时使用<br>
     */
    private Double discountAmount;

    /**
     * 折扣比例，优惠类型为限时折扣时使用.<br>
     */
    private Double discountPercent;

    /**
     * 保底价，单位为元<br>
     * 优惠类型为保底优惠时使用<br>
     */
    private Double bottomAmount;

    /**
     * 封顶价，单位为元<br>
     * 优惠类型为封顶优惠时使用<br>
     */
    private Double topAmount;

    /**
     * 参加活动的账单科目列表
     */
    private List<String> billSubjectList;

    /**
     * 关联账单科目
     */
    private List<String> relatedSubjectList;

    /**
     * 满赠活动的赠品
     */
    private GiftProduct giftProduct;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getFullCostAmount() {
        return fullCostAmount;
    }

    public void setFullCostAmount(Double fullCostAmount) {
        this.fullCostAmount = fullCostAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(Double bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public Double getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(Double topAmount) {
        this.topAmount = topAmount;
    }

    public List<String> getBillSubjectList() {
        return billSubjectList;
    }

    public void setBillSubjectList(List<String> billSubjectList) {
        this.billSubjectList = billSubjectList;
    }

    public List<String> getRelatedSubjectList() {
        return relatedSubjectList;
    }

    public void setRelatedSubjectList(List<String> relatedSubjectList) {
        this.relatedSubjectList = relatedSubjectList;
    }

    public GiftProduct getGiftProduct() {
        return giftProduct;
    }

    public void setGiftProduct(GiftProduct giftProduct) {
        this.giftProduct = giftProduct;
    }

}
