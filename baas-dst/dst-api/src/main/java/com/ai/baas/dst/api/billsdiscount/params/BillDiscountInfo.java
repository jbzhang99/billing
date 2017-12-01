package com.ai.baas.dst.api.billsdiscount.params;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 账单优惠产品信息
 * @author wangluyang
 *
 */
public class BillDiscountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单优惠产品ID
     */
    private String discountId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 优惠活动名称
     */
    private String discountName;

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
     * 是否所有产品参与优惠
     * 0:否
     * 1:是
     */
    private String allPrdDiscount;
    
    /**
     * 关联账单科目
     */
    private List<String> relatedSubjectList;
    
    /**
     * 扩展信息列表
     */
    private List<ExtendInfo> extendInfoList;
    
    /**
     * 满赠赠品信息
     */
    private GiftProductResponseVo giftProduct;

    public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getAllPrdDiscount() {
		return allPrdDiscount;
	}

	public void setAllPrdDiscount(String allPrdDiscount) {
		this.allPrdDiscount = allPrdDiscount;
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

	public GiftProductResponseVo getGiftProduct() {
		return giftProduct;
	}

	public void setGiftProduct(GiftProductResponseVo giftProduct) {
		this.giftProduct = giftProduct;
	}
}
