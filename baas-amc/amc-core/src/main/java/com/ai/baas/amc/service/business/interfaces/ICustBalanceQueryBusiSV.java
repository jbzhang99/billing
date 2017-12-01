package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 可用余额（客户级别）分页查询业务接口定义
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author fanpw
 */
public interface ICustBalanceQueryBusiSV {

    /**
     * 可用余额分页查询
     * @param request
     * @return
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<UsableBalanceVo> queryUsableBalance(UsableBalanceQueryRequest request)
            throws BusinessException;

}
