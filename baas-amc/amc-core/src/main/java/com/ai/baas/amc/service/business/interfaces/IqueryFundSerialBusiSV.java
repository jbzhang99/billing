package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

public interface IqueryFundSerialBusiSV {
    PageInfo<FundSerialInfo> queryFundSerialList(QueryFundSerialRequest queryFundSerialRequest) throws BusinessException,SystemException;
}
