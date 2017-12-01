package com.ai.baas.amc.api.billQuery.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单详情查询返回类
 * Date: 2016年4月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangkai16
 */
public class BillDetailOutput extends BaseResponse{
    private static final long serialVersionUID = -423423479L;
    /**
     * 返回码
     */
    private String returnCode;
    /**
     * 消息流水
     */
    private String tradeSeq;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 客户等级
     */
    private Long custGrade;
    /**
     * 客户名称
     */
    private String custName;

    /**
     * 账期
     */
    private String queryTime;

    /**
     * 服务列表
     */
    private List<ServiceParams> serviceList; 
    

    public Long getCustGrade() {
        return custGrade;
    }
    public void setCustGrade(Long custGrade) {
        this.custGrade = custGrade;
    }
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getTradeSeq() {
        return tradeSeq;
    }
    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq;
    }
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

    public String getQueryTime() {
        return queryTime;
    }
    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public List<ServiceParams> getServiceList() {
        return serviceList;
    }
    public void setServiceList(List<ServiceParams> serviceList) {
        this.serviceList = serviceList;
    }

    
    
}
