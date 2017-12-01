package com.ai.baas.amc.api.oweinfoquery.param;

import java.io.Serializable;

/**
 * 账单明细信息.<br>
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class ChargeDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目ID
     */
    private long subjectId;
    
    /**
     * 科目名称
     */
    private String subjectName;
    
    /**
     * 欠费金额，单位为厘
     */
    private long balance;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
