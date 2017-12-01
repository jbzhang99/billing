package com.ai.baas.amc.api.oweinfoquery.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 欠费明细查询返回报文.<br>
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweDetailInfoQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     * 租户Id
     */
    private String tenantId;

    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 客户等级
     */
    private String custGrade;
    
    /**
     * 欠费开始时间
     */
    private String unsettledMonth;
    
    /**
     * 合计欠费金额，单位为厘
     */
    private long balance;
    
    /**
     * 欠费明细信息列表
     */
    private List<OweDetailInfo> oweDetailInfos;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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

    public String getUnsettledMonth() {
        return unsettledMonth;
    }

    public void setUnsettledMonth(String unsettledMonth) {
        this.unsettledMonth = unsettledMonth;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<OweDetailInfo> getOweDetailInfos() {
        return oweDetailInfos;
    }

    public void setOweDetailInfos(List<OweDetailInfo> oweDetailInfos) {
        this.oweDetailInfos = oweDetailInfos;
    }
}
