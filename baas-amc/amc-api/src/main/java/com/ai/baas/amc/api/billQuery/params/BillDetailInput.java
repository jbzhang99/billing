package com.ai.baas.amc.api.billQuery.params;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.baas.amc.api.billQuery.interfaces.IBillQuerySV;
import com.ai.opt.base.vo.BaseInfo;

/**
 * 账单详情查询入参
 * Date: 2016年4月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangkai16
 */
public class BillDetailInput extends BaseInfo{

    private static final long serialVersionUID = -46456679L;
    
    /**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR
     */
    @NotBlank(message="消息流水不能为空",groups={IBillQuerySV.GetBillDetail.class})
    //@Size(max=32,groups={IBillQuerySV.GetBillDetail.class})
    private String tradeSeq;

    /**
     * 客户ID<br>
     *  必填<br>
     * VARCHAR(32)
     */
    @NotNull(message="客户ID不能为空",groups={IBillQuerySV.GetBillDetail.class})
    //@Size(max=32,groups={IBillQuerySV.GetBillDetail.class})
    private Long custId;
    
    /**
     * 账单流水号<br>
     * VARCHAR(32)
     */
    //@NotNull(message="账单流水号不能为空",groups={IBillQuerySV.GetBillDetail.class})
    //@Size(max=32,groups={IBillQuerySV.GetBillDetail.class})
    //private Long invoiceSeq;
    
    /**
     * 账期<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
    @NotNull(message="账期不能为空",groups={IBillQuerySV.GetBillDetail.class})
    @Size(max=32,groups={IBillQuerySV.GetBillDetail.class})
    private String queryTime;

    public String getTradeSeq() {
        return tradeSeq;
    }
    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq;
    }
    

    public Long getCustId() {
        return custId;
    }
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    public String getQueryTime() {
        return queryTime;
    }
    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

}
