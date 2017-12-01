package com.ai.baas.amc.api.deduct.param;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 普通扣款请求.<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class DeductRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 系统ID，必填
     */
    private String systemId;

    /**
     * 外部单号，必填
     */
    private String externalId;

    /**
     * 业务操作类型，必填
     */
    private String businessCode;

    /**
     * 账户ID，必填
     */
    private String accountId;

    /**
     * 扣款总金额，必填
     */
    private long totalAmount;
    
    /**
     * 专款用户ID，默认0非专款，可选 <br>
     * 1.指定用户ID－本次扣款只从专款中扣减 <br>
     * 2.未指定－本次扣款只从非专款中扣减 <br>
     */
    private long subsId;

    /**
     * 交易摘要，可空 <br>
     * 交易摘要指定科目账本的扣款优先处理，剩下的扣款金额使用系统默认规则处理 <br>
     */
    private List<TransSummary> transSummary;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<TransSummary> getTransSummary() {
        return transSummary;
    }

    public void setTransSummary(List<TransSummary> transSummary) {
        this.transSummary = transSummary;
    }

    public long getSubsId() {
        return subsId;
    }

    public void setSubsId(long subsId) {
        this.subsId = subsId;
    }
}
