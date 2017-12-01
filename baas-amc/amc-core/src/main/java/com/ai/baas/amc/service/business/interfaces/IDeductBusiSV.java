package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.deduct.param.DeductRequest;
import com.ai.opt.base.exception.BusinessException;

/**
 * 扣款业务层<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IDeductBusiSV {

    /**
     * 单次扣款
     * 
     * @param param
     * @return
     * @throws BusinessException
     * @author lilg
     * @ApiDocMethod
     * @ApiCode
     */
    String deductFund(DeductRequest request) throws BusinessException;
    
}
