package com.ai.baas.batch.client.api.order.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.api.order.interfaces.OrderEntrySV;
import com.ai.baas.batch.client.mainflow.ProcessOrder;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class OrderEntrySVImpl implements OrderEntrySV{
    @Autowired
    ProcessOrder  processOrder;
    private static final Log LOG = LogFactory.getLog(OrderEntrySV.class);
    
    @Override
    public BaseResponse instanceOrder(String json) throws BusinessException, SystemException {
        String isSuccess="";
        BaseResponse resultCode = new BaseResponse();
        LOG.info("【获取到订单信息，执行订购处理】"); 
        PropertiesUtil.load("batch.properties");
        try {
        	isSuccess = processOrder.processOrder(json);
        }catch(Exception e) {
            LOG.error("【订单消息处理错误："+e.getMessage()+"】",e);
            resultCode.setResponseHeader(new ResponseHeader(false, "999999", "unknown error"));
            return resultCode;
        }
        if(isSuccess.equals("000000")){
            LOG.info("【order success】"); 
            resultCode.setResponseHeader(new ResponseHeader(true, isSuccess, "success"));
            return resultCode;
        }else{
            LOG.info("【order fail】"); 
            resultCode.setResponseHeader(new ResponseHeader(false, isSuccess, isSuccess));
            return resultCode;
        }
    }

}
