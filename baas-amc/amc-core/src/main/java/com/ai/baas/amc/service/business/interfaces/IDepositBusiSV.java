package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.opt.base.exception.BusinessException;

/**
 * 存款服务
 *
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IDepositBusiSV {

    /**
     * 存款接口
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public String depositFund(DepositRequest request) throws BusinessException;
    
}
