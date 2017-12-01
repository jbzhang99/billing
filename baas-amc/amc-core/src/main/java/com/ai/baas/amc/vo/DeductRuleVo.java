package com.ai.baas.amc.vo;

/**
 * 销账规则
 * Created by jackieliu on 16/3/31.
 */
public class DeductRuleVo {
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 资金科目
     */
    private Long fundSubject;
    /**
     * 账单科目
     */
    private Long feeSubject;

    public DeductRuleVo(){}

    public DeductRuleVo(String tenantId,Long fundSubject,Long feeSubject){
        this.tenantId = tenantId;
        this.fundSubject = fundSubject;
        this.feeSubject = feeSubject;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getFundSubject() {
        return fundSubject;
    }

    public void setFundSubject(Long fundSubject) {
        this.fundSubject = fundSubject;
    }

    public Long getFeeSubject() {
        return feeSubject;
    }

    public void setFeeSubject(Long feeSubject) {
        this.feeSubject = feeSubject;
    }
}
