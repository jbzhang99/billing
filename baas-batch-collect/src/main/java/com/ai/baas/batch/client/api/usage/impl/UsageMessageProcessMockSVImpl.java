package com.ai.baas.batch.client.api.usage.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.api.usage.interfaces.IUsageMessageProcessMockSV;
import com.ai.baas.batch.client.api.usage.params.UsageRecord;
import com.ai.baas.batch.client.basethread.SendRtmThread;
import com.ai.baas.batch.client.core.GetRecordDetail;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.extension.DubboRestResponse;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

@Service
@Component
public class UsageMessageProcessMockSVImpl implements IUsageMessageProcessMockSV{

    private static final Log LOG = LogFactory.getLog(UsageMessageProcessMockSVImpl.class);

    @Resource
    private GetRecordDetail recordDetail;

    @Override
    public BaseResponse process(UsageRecord record) throws BusinessException, SystemException {
        BaseResponse response = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        //启动线程
        PropertiesUtil.load("batch.properties");
        
        String rtmUrl = getUrl.getInstance().getRtm();
        SendRtmThread sendRtm = new SendRtmThread();
    	sendRtm.setThreadName("SEND_RTM__" + 1);
    	sendRtm.setInterval(1);
    	sendRtm.setRtmUrl(rtmUrl);
    	sendRtm.start();
        
        
        try {
            recordDetail.processUsageMessages(record.getServiceId(),standardizeUsageJson(record.getUsageJson()));
            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("使用量模拟计费成功");
        } catch (Exception e) {
            LOG.error("使用量模拟处理出现错误",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("使用量模拟计费失败:"+e.getMessage());
        }
        response.setResponseHeader(header);
        
        //退出线程
        sendRtm.setExitFlag(true);
        
        return response;
    }

    private String standardizeUsageJson(String json) {
        DubboRestResponse res = new DubboRestResponse();
        res.setResultCode("000000");
        res.setData(json);
        res.setResultMessage("使用量包装成功");
        return JSON.toJSONString(res);
    }
}
