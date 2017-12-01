package com.ai.baas.ccp.api.subscribeinvalid2stopnotice.impl;

import com.ai.baas.ccp.api.subscribeinvalid2stopnotice.interfaces.ISubscribeInvalid2StopNoticeSV;
import com.ai.baas.ccp.api.subscribeinvalid2stopnotice.params.SubscribeInvalid2StopNoticeRequest;
import com.ai.baas.ccp.service.factory.ServiceFactory;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class SubscribeInvalid2StopNoticeSVImpl implements ISubscribeInvalid2StopNoticeSV {

    @Override
    public BaseResponse stopNotice(SubscribeInvalid2StopNoticeRequest request) {
        
        ServiceFactory.getISubscribeInvalid2StopNoticeBusiSV().stopNotice(request);
        
        BaseResponse response = new BaseResponse();
        response.setResponseHeader(new ResponseHeader(true, "000000", "success"));
        return response;
    }

}
