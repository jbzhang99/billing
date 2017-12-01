package com.ai.baas.amc.dao.mapper.bo;

/**
 * 客户账本余额汇总信息.<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class CustBalanceInfo {

    private String custId;
    
    private Long totalBalance;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Long getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Long totalBalance) {
        this.totalBalance = totalBalance;
    }
}
