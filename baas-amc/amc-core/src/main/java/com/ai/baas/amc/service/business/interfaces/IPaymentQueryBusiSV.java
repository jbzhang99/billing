package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.paymentquery.param.PaymentLog;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 缴费查询业务层接口定义
 *
 * Date: 2016年4月17日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IPaymentQueryBusiSV {

    /**
     * 缴费查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<PaymentLog> queryPaymentLog(PaymentLogQueryRequest request) throws BusinessException;
    
}
