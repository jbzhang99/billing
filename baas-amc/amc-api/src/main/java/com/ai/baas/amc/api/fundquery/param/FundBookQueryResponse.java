package com.ai.baas.amc.api.fundquery.param;

import java.util.Collections;
import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 余额查询结果
 * 
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author fanpw
 */
public class FundBookQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 账户ID
     */
    private String accountId;

    /**
     * 账户余额
     */
    private long balance;

    /**
     * 账本列表
     */
    private List<FundBook> fundBooks;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<FundBook> getFundBooks() {
        return Collections.unmodifiableList(fundBooks);
    }

    public void setFundBooks(List<FundBook> fundBooks) {
        this.fundBooks = fundBooks;
    }

}
