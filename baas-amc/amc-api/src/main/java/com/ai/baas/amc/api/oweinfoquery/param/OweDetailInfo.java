package com.ai.baas.amc.api.oweinfoquery.param;

import java.io.Serializable;
import java.util.List;

/**
 * 欠费明细信息.<br>
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 账期月份
     */
    private String accDate;
    
    /**
     * 账户ID
     */
    private String acctId;

    /**
     * 服务号码
     */
    private String serviceNum;
    
    /**
     * 账单明细信息列表
     */
    private List<ChargeDetailInfo> chargeDetailInfos;

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public List<ChargeDetailInfo> getChargeDetailInfos() {
        return chargeDetailInfos;
    }

    public void setChargeDetailInfos(List<ChargeDetailInfo> chargeDetailInfos) {
        this.chargeDetailInfos = chargeDetailInfos;
    }
}
