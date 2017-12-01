package com.ai.baas.amc.api.queryinoutfundserial.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.queryinoutfundserial.interfaces.IqueryFundSerialSV;
import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialResponse;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IqueryFundSerialBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class QueryFundSerialSVImpl implements IqueryFundSerialSV{

    @Autowired
    private IqueryFundSerialBusiSV iqueryFundSerialBusiSV;
    @Override
    public QueryFundSerialResponse queryFundSerialList(QueryFundSerialRequest queryFundSerialRequest)
            throws BusinessException, SystemException {
        if(queryFundSerialRequest==null){
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if(StringUtil.isBlank(queryFundSerialRequest.getTenantId())){
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户id不能为空");
        }
       PageInfo<FundSerialInfo> pageInfo= iqueryFundSerialBusiSV.queryFundSerialList(queryFundSerialRequest);
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        QueryFundSerialResponse queryFundSerialResponse=new QueryFundSerialResponse();
        queryFundSerialResponse.setResponseHeader(responseHeader);
        queryFundSerialResponse.setPageInfo(pageInfo);
        return queryFundSerialResponse;
    }

}
