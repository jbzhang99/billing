package com.ai.baas.amc.api.billQuery.params;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.baas.amc.api.billQuery.interfaces.IBillQuerySV;
import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单查询入参
 * Date: 2016年4月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author KAI
 */
public class BillInput extends BaseInfo{

    private static final long serialVersionUID = -46666979L;
    /**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR
     */
    @NotBlank(message="消息流水不能为空",groups={IBillQuerySV.GetBillInfo.class})
    //@Size(max=32,groups={IBillQuerySV.GetBillInfo.class})
    private String tradeSeq;
    
//    /**
//     * 账单流水号<br>
//     * NUMBER(15)
//     */
//    private Long invoiceSeq;
//    
    /**
     * 客户名称<br>
     * VARCHAR(32)
     */
    @Size(max=32,groups={IBillQuerySV.GetBillInfo.class})
    private String custName;
    
    /**
     * 客户等级<br>
     * VARCHAR(20)
     */
    private String  custGrade;
    
    /**
     * 查询时间<br>
     * 组成：格式YYYYMMDDHH24MISS 缺省是系统时间<br>
     * 必填<br>
     * VARCHAR(14)
     */
    @NotBlank(message="查询时间不能为空",groups={IBillQuerySV.GetBillInfo.class})
    @Size(max=14,groups={IBillQuerySV.GetBillInfo.class})
    private String queryTime;
    
    /**
     * 服务标识<br>
     * VARCHAR(32)
     */
    @Size(max=32,groups={IBillQuerySV.GetBillInfo.class})
    private String serviceId;
    
    /**
     * 页码<br>
     * NUMBER(9)
     */
    private Integer pageNumber;
    /**
     * 每页条数<br>
     * NUMBER(9)
     */
    private Integer pageSize;
    
    public String getTradeSeq() {
        return tradeSeq;
    }
    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq;
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
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public String getCustGrade() {
        return custGrade;
    }
    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }
    public Integer getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }






    
    
    
}
