package com.ai.baas.smc.api.cacherefresh.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.ai.baas.smc.api.cacherefresh.interfaces.ICacheRefreshSV;
import com.ai.baas.smc.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.util.SmcCacheUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class CacheRefreshSVImpl implements ICacheRefreshSV {
    private static final Logger LOGGER = LogManager.getLogger(CacheRefreshSVImpl.class);

    @Autowired
    private transient ApplicationContext context;

    public BaseResponse refresh() throws BusinessException, SystemException {
        LOGGER.info("开始刷新mcs缓存...");
        SmcCacheUtil.refreshMcs(context);
        LOGGER.info("刷新mcs缓存结束");
        BaseResponse response = new BaseResponse();
        response.setResponseHeader(new ResponseHeader(true, SmcExceptCodeConstant.SUCCESS, "成功"));
        return response;
    }

}
