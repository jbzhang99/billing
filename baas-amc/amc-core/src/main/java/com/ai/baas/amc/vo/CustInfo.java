package com.ai.baas.amc.vo;

import java.io.Serializable;

/**
 * 客户信息实体VO
 *
 * Date: 2016年4月15日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class CustInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 客户等级
     */
    private String custGrade;

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
    
}
