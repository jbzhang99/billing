package com.ai.baas.amc.api.billQuery.params;

import java.io.Serializable;
import java.util.List;

import javax.security.auth.Subject;

public class Bill implements Serializable{
    
    private static final long serialVersionUID = -49894593979L;

    /**
     * 客户等级
     */
    private String  custGrade;
    /**
     * 客户ID
     */
    private Long custId;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 账期
     */
    private String billDuration;
    /**
     * 原始费用
     */
    private Long orgFee;
    /**
     * 优惠费用
     */
    private Long disFee;
    /**
     * 调整费用
     */
    private Long adjustFee;
    /**
     * 总费用
     */
    private Long totalFee;
    



    public String getCustGrade() {
        return custGrade;
    }

    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }


    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getBillDuration() {
        return billDuration;
    }
    public void setBillDuration(String billDuration) {
        this.billDuration = billDuration;
    }
    public Long getOrgFee() {
        return orgFee;
    }
    public void setOrgFee(Long orgFee) {
        this.orgFee = orgFee;
    }
    public Long getDisFee() {
        return disFee;
    }
    public void setDisFee(Long disFee) {
        this.disFee = disFee;
    }
    public Long getAdjustFee() {
        return adjustFee;
    }
    public void setAdjustFee(Long adjustFee) {
        this.adjustFee = adjustFee;
    }
    public Long getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
    
}
