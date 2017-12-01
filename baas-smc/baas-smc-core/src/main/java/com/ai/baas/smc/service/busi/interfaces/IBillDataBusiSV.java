package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.billdata.param.QueryBillDataDetailRequest;
import com.ai.baas.smc.api.billdata.param.QueryBillDataRequest;
import com.ai.baas.smc.api.billdata.param.QueryBillDataResponse;
import com.ai.baas.smc.api.billdata.param.QueryBillDetailResponse;
import com.ai.opt.base.exception.BusinessException;

public interface IBillDataBusiSV {
    /**
     * 账单信息查询<br>
     * 
     * @param queryBillDataRequest
     * @return
     * @author wangjl9
     * @ApiDocMethod
     */
    QueryBillDataResponse queryBillData(QueryBillDataRequest queryBillDataRequest)
            throws BusinessException;

    /**
     * 账单信息查询<br>
     * 
     * @param queryBillDataDetailRequest
     * @return
     * @author wangjl9
     * @ApiDocMethod
     */
    QueryBillDetailResponse queryBillDataDetail(
            QueryBillDataDetailRequest queryBillDataDetailRequest) throws BusinessException;
}
