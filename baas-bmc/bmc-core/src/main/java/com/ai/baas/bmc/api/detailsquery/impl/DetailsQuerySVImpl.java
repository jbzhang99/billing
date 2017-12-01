package com.ai.baas.bmc.api.detailsquery.impl;

import com.ai.baas.bmc.api.detailsquery.interfaces.IDetailsQuerySV;
import com.ai.baas.bmc.api.detailsquery.params.DetailVo;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryResponse;
import com.ai.baas.bmc.service.business.interfaces.IDetailsQueryBusiness;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(validation="true")
@Component
public class DetailsQuerySVImpl implements IDetailsQuerySV{

    @Autowired
    private IDetailsQueryBusiness detailsQueryBusiness;

    @Override
    public DetailsQueryResponse queryDetails(DetailsQueryRequest request) throws BusinessException, SystemException {
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException("888888", "[租户Id]不能为空");
        }
        List<DetailVo> details = detailsQueryBusiness.getDetails(request);
        DetailsQueryResponse response = new DetailsQueryResponse();
        response.setDetails(details);
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("000000");
        responseHeader.setIsSuccess(true);
        responseHeader.setResultMessage("使用记录查询成功");
        response.setResponseHeader(responseHeader);
        return response;
    }
}
