package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyle;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleListInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleListVoResponse;
import com.ai.opt.base.exception.BusinessException;

public interface IQueryBillStyleBusiSV {

    /**
     * 查询账单样式<br>
     * 
     * @param queryBillStyle
     * @return
     * @author wangjl9
     * @ApiDocMethod
     */
    QueryBillStyleInfo queryBillStyle(QueryBillStyle queryBillStyle) throws BusinessException;

    /**
     * 查询账单样式列表<br>
     * 
     * @param queryBillStyleListInfo
     * @return
     * @author wangjl9
     * @ApiDocMethod
     */
    QueryBillStyleListVoResponse queryBillStyleList(QueryBillStyleListInfo queryBillStyleListInfo)
            throws BusinessException;
}
