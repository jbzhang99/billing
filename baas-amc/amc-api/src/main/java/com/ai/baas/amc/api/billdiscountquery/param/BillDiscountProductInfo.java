package com.ai.baas.amc.api.billdiscountquery.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 账单优惠产品信息
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class BillDiscountProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单优惠产品ID
     */
    private String productId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 优惠活动名称
     */
    private String productName;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 生效日期
     */
    private Timestamp effectDate;

    /**
     * 失效日期
     */
    private Timestamp expireDate;

    /**
     * 状态: <br>
     * 0:失效<br>
     * 1:生效<br>
     * 2:待生效<br>
     */
    private String status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 账单优惠类型<br>
     * mz:满赠<br>
     * mj:满减<br>
     * dz:限时折扣<br>
     * bd:保底<br>
     * fd:封顶<br>
     * 
     */
    private String discountType;
    
    /**
     * 关联账单科目
     */
    private List<String> relatedSubjectList;
    
    /**
     * 扩展信息列表
     */
    private List<ExtendInfo> extendInfoList;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Timestamp getEffectDate() {
        if (effectDate == null) {
            return null;
        }

        return new Timestamp(effectDate.getTime());
    }

    public void setEffectDate(Timestamp effectDate) {
        if (effectDate != null) {
            this.effectDate = new Timestamp(effectDate.getTime());
        }
    }

    public Timestamp getExpireDate() {
        if (expireDate == null) {
            return null;
        }

        return new Timestamp(expireDate.getTime());
    }

    public void setExpireDate(Timestamp expireDate) {
        if (expireDate != null) {
            this.expireDate = new Timestamp(expireDate.getTime());
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        if (createTime == null) {
            return null;
        }

        return new Timestamp(createTime.getTime());
    }

    public void setCreateTime(Timestamp createTime) {
        if (createTime != null) {
            this.createTime = new Timestamp(createTime.getTime());
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public List<String> getRelatedSubjectList() {
        return relatedSubjectList;
    }

    public void setRelatedSubjectList(List<String> relatedSubjectList) {
        this.relatedSubjectList = relatedSubjectList;
    }

    public List<ExtendInfo> getExtendInfoList() {
        return extendInfoList;
    }

    public void setExtendInfoList(List<ExtendInfo> extendInfoList) {
        this.extendInfoList = extendInfoList;
    }  
    
}
