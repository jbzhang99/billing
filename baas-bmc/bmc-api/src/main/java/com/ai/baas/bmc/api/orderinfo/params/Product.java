package com.ai.baas.bmc.api.orderinfo.params;

import java.io.Serializable;
import java.util.List;

/**
 * 产品列表 Date: 2016年3月22日 Copyright (c) 2016 asiainfo.com
 * 
 * @author caoyf
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 2814045806780260212L;

    /**
     * 产品ID 必填 VARCHAR(32)
     */
    private String productId;

    /**
     * 产品实例ID VARCHAR(32)
     */
    private String subsProductId;

    /**
     * 产品类型 取值范围：dr:详单, bill:账单 必填 VARCHAR(32)
     */
    private String productType;

    /**
     * 产品数量 同一个产品被客户订购的数量 必填 NUMBER(9)
     */
    private Integer productNumber;

    /**
     * 产品价格,整数，单位分
     */
    private long subsProductPrice;

    /**
     * 是否触发抵扣
     */
    private String deductable;

    /**
     * 赠送标识 标识该产品是否为一个赠送的产品。取值范围：Y：是赠送；N：不是赠送。 VARCHAR(1)
     */
    private String resBonusFlag;

    /**
     * 生效日期 格式：YYYYMMDDHH24MISS 必填 VARCHAR(14)
     */
    private String activeTime;

    /**
     * 失效日期 格式：YYYYMMDDHH24MISS 必填 VARCHAR(14)
     */
    private String inactiveTime;

    /**
     * 产品扩展信息列表 list
     */
    private List<ProductExt> productExtInfoList;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getResBonusFlag() {
        return resBonusFlag;
    }

    public void setResBonusFlag(String resBonusFlag) {
        this.resBonusFlag = resBonusFlag;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(String inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public List<ProductExt> getProductExtInfoList() {
        return productExtInfoList;
    }

    public void setProductExtInfoList(List<ProductExt> productExtInfoList) {
        this.productExtInfoList = productExtInfoList;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSubsProductId() {
        return subsProductId;
    }

    public void setSubsProductId(String subsProductId) {
        this.subsProductId = subsProductId;
    }

    public long getSubsProductPrice() {
        return subsProductPrice;
    }

    public void setSubsProductPrice(long subsProductPrice) {
        this.subsProductPrice = subsProductPrice;
    }

    public String getDeductable() {
        return deductable;
    }

    public void setDeductable(String deductable) {
        this.deductable = deductable;
    }

}
