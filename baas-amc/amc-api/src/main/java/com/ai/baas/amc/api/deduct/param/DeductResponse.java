package com.ai.baas.amc.api.deduct.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 扣款结果返回报文.<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class DeductResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     * 扣款流水号
     */
    private String paySerialCode;

    public String getPaySerialCode() {
        return paySerialCode;
    }

    public void setPaySerialCode(String paySerialCode) {
        this.paySerialCode = paySerialCode;
    }

}