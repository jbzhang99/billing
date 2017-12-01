package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;

/**
 * 
 * Date: 2016年7月11日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public class TotalBillInfo implements Serializable{

    private static final long serialVersionUID = -2171979816075540726L;
    /**
     * 月份
     */
    private String date;
    /**
     * 金额
     */
    private long amount;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    
}
