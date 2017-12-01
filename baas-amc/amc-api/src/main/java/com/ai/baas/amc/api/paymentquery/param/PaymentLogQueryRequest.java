package com.ai.baas.amc.api.paymentquery.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

/**
 * 充值缴费列表查询请求
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class PaymentLogQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 客户等级
     */
    private String custGrade;
    
    /**
     * 充值流水号
     */
    private String paySerialCode;
    
    /**
     * 开始时间
     */
    private Timestamp startTime;

    /**
     * 结束时间
     */
    private Timestamp endTime;
    
    /**
     * 最低充值金额，单位为元
     */
    private Double bottomAmount; 
    
    /**
     * 最高充值金额，单位为元
     */
    private Double topAmount;
    
    /**
     * 分页信息
     */
    private PageInfo<PaymentLog> pageInfo;

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

    public String getPaySerialCode() {
        return paySerialCode;
    }

    public void setPaySerialCode(String paySerialCode) {
        this.paySerialCode = paySerialCode;
    }

    public Timestamp getStartTime() {
        if (startTime == null) {
            return null;
        }

        return new Timestamp(startTime.getTime());
    }

    public void setStartTime(Timestamp startTime) {
        if (startTime != null) {
            this.startTime = new Timestamp(startTime.getTime());
        }
    }

    public Timestamp getEndTime() {
        if (endTime == null) {
            return null;
        }

        return new Timestamp(endTime.getTime());
    }

    public void setEndTime(Timestamp endTime) {
        if (endTime != null) {
            this.endTime = new Timestamp(endTime.getTime());
        }
    }

    public Double getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(Double bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public Double getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(Double topAmount) {
        this.topAmount = topAmount;
    }

    public PageInfo<PaymentLog> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<PaymentLog> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
