package com.ai.baas.amc.api.oweinfo.params;

import com.ai.opt.base.vo.BaseInfo;

public class OweInfoCreateRequest extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 账户标识
     */
    private String acctId;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 客户等级
     */
    private String custGrade;

    /**
     * 客户标识
     */
    private String custId;

    /**
     * 最后未销帐月份
     */
    private String month;

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustGrade() {
        return custGrade;
    }

    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
