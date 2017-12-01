package com.ai.baas.amc.api.billQuery.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单查询返回类
 * Date: 2016年4月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangkai16
 */
public class BillOutput extends BaseResponse {
    private static final long serialVersionUID = -49894829978979L;
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
     * 账单列表
     */
    private PageInfo<Bill> billList;

    /**
     * 查询时间
     */
    private String queryTime;
    
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
    public PageInfo<Bill> getBillList() {
        return billList;
    }
    public void setBillList(PageInfo<Bill> billList) {
        this.billList = billList;
    }
    public String getQueryTime() {
        return queryTime;
    }
    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    /**
        响应报文 返回码 billInfo    returnCode  Y   VARCHAR(14) BaaS-000000成功；其他失败
        响应报文    消息流水    billInfo    tradeSeq    Y   VARCHAR(32) 等于请求报文中的消息流水
        响应报文    租户ID    billInfo    tenantId    Y   VARCHAR(32) 
        响应报文    账单列表    billInfo    billList    *   List    可以是空链表，按账期的账单列表
    
        账单列表    账单流水号   billList    invoiceSeq  Y   NUMBER(15)  
        账单列表    客户名称    billList    custName    Y   VARCHAR(32) 
        账单列表    账期  billList    billDuration    Y   VARCHAR(32) 月账期：YYYYMM
        账单列表    原始费用    billList    orgFee  N   NUMBER(32)  
        账单列表    优惠费用    billList    disFee  N   NUMBER(32)  折扣费用，单位：厘，整数。计费中心在接口中转换时不足1厘的小数部分四舍五入
        账单列表    调整费用    billList    adjustFee   N   NUMBER(32)  调整费用，单位：厘，整数。计费中心在接口中转换时不足1厘的小数部分四舍五入。正数为调增、负数为调减，0为未调整。     
        账单列表    总费用 billList    totalfee    Y   NUMBER(32)  该用户本月账单总额。单位：厘，整数。计费中心在接口中转换时不足1厘的小数部分四舍五入
    
        响应报文    当前页码    billInfo    pageNumber  N   VARCHAR(32) 当前页码
        响应报文    总页码 billInfo    pageSize    N   VARCHAR(32) 总条数(billList的条数)
        响应报文    查询时间    billInfo    queryTime   N   VARCHAR(14) 

     */

}
