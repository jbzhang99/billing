package com.ai.baas.amc.dao.mapper.bo;

/**
 * 客户欠费汇总信息
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class CustOweInfo {
    
    private String custId;
    
    private String ownFee;
    
    private String unsettledMonth;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getOwnFee() {
        return ownFee;
    }

    public void setOwnFee(String ownFee) {
        this.ownFee = ownFee;
    }

    public String getUnsettledMonth() {
        return unsettledMonth;
    }

    public void setUnsettledMonth(String unsettledMonth) {
        this.unsettledMonth = unsettledMonth;
    }
}
