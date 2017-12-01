package com.ai.baas.amc.api.deposit.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 存款结果返回报文
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 AILK <br>
 * 
 * @author fanpw
 */
public class DepositResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     * 存款流水号
     */
    private String paySerialCode;

    public String getPaySerialCode() {
        return paySerialCode;
    }

    public void setPaySerialCode(String paySerialCode) {
        this.paySerialCode = paySerialCode;
    }

}
