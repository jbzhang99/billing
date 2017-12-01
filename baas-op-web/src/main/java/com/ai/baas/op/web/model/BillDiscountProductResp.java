package com.ai.baas.op.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;

/**
 * 账单优惠产品信息
 */
public class BillDiscountProductResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tenantId;    //租户ID
    private String productId;   //账单优惠产品ID
    private String productName; //优惠活动名称
    private String discountType;//账单优惠类型
    private String priority;    //优先级
    private String status;      // 状态
    private String remark;      //备注
    
    private Timestamp effectDate;//生效日期
    private Timestamp expireDate;//失效日期
    private Timestamp createTime;//创建时间
    
    private String fullCostAmount;  //满赠（满减）到达金额，单位为元
    private String discountAmount;  //扣减金额，单位为元
    private String discountPercent; //折扣比例，优惠类型为限时折扣时必填
    private String bottomAmount;    //保底价，单位为元
    private String topAmount;       //封顶价，单位为元
    
    private String relatedSubjectId;                //关联账单科目（满减、保底、封顶时账单科目）
    private List<GnSubjectInfoVo> billSubjectList;  //参加活动的账单科目列表
    
    private List<ProductInfo> giftProductList;      //赠品：赠送业务列表，必填
    private String giftActiveMode;                  //赠品：生效方式，必填（ 0:立即生效 1:下月生效 2:指定日期到账）
    private String giftActivePeriod;                //赠品：赠送业务周期，必填（0:一个月 1:三个月 2:指定时间）
    private String giftEffectDate;                  //赠品：生效时间
    
    private String startTime;   //限时折扣：优惠开始时间
    private String endTime;     //限时折扣：优惠结束时间
    
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

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

    public String getFullCostAmount() {
        return fullCostAmount;
    }

    public void setFullCostAmount(String fullCostAmount) {
        this.fullCostAmount = fullCostAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(String bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public String getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(String topAmount) {
        this.topAmount = topAmount;
    }

    public List<GnSubjectInfoVo> getBillSubjectList() {
        return billSubjectList;
    }

    public void setBillSubjectList(List<GnSubjectInfoVo> billSubjectList) {
        this.billSubjectList = billSubjectList;
    }

    public List<ProductInfo> getGiftProductList() {
        return giftProductList;
    }

    public void setGiftProductList(List<ProductInfo> giftProductList) {
        this.giftProductList = giftProductList;
    }

    public String getGiftActiveMode() {
        return giftActiveMode;
    }

    public void setGiftActiveMode(String giftActiveMode) {
        this.giftActiveMode = giftActiveMode;
    }

    public String getGiftActivePeriod() {
        return giftActivePeriod;
    }

    public void setGiftActivePeriod(String giftActivePeriod) {
        this.giftActivePeriod = giftActivePeriod;
    }

    public String getGiftEffectDate() {
        return giftEffectDate;
    }

    public void setGiftEffectDate(String giftEffectDate) {
        this.giftEffectDate = giftEffectDate;
    }

    public String getRelatedSubjectId() {
        return relatedSubjectId;
    }

    public void setRelatedSubjectId(String relatedSubjectId) {
        this.relatedSubjectId = relatedSubjectId;
    }
    
}
