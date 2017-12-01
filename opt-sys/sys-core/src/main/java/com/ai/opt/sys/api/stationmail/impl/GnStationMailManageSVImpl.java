package com.ai.opt.sys.api.stationmail.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.stationmail.interfaces.IGnStationMailManageSV;
import com.ai.opt.sys.api.stationmail.param.StationMails;
import com.ai.opt.sys.service.business.interfaces.IGnStationMailBusiSV;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(validation = "true")
@Component
public class GnStationMailManageSVImpl implements IGnStationMailManageSV{

    @Autowired
    private IGnStationMailBusiSV gnStationMailBusiSV;

    @Override
    public BaseResponse saveStationMails(StationMails stationMails) throws BusinessException, SystemException {
        BaseResponse response = null;
        try {
            gnStationMailBusiSV.saveStationMails(stationMails);
            response = new BaseResponse();
            ResponseHeader header = new ResponseHeader();
            header.setIsSuccess(true);
            header.setResultMessage("发送成功");
            header.setResultCode("000000");
            response.setResponseHeader(header);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e){
            throw new SystemException(e);
        }

        return response;
    }
}
