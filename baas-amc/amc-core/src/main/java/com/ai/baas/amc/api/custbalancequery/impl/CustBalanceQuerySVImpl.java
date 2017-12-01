package com.ai.baas.amc.api.custbalancequery.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryResponse;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceVo;
import com.ai.baas.amc.api.custbalancequery.interfaces.ICustBalanceQuerySV;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.ICustBalanceQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 可用余额查询服务（客户级别）.<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Component
public class CustBalanceQuerySVImpl implements ICustBalanceQuerySV {
    
    private static final Logger LOG = LogManager.getLogger(CustBalanceQuerySVImpl.class);
    
    @Autowired
    private ICustBalanceQueryBusiSV balanceQueryBusiSV;

    @Override
    public UsableBalanceQueryResponse queryUsableBalance(UsableBalanceQueryRequest request)
            throws BusinessException, SystemException {
        LOG.info("可用余额（客户级别）分页查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "可用余额分页查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (request.getPageInfo() == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取参数失败:分页信息不能为空");
        }
        if (request.getPageInfo().getPageNo() == null || request.getPageInfo().getPageNo() == 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取参数失败:查询页码不能为空");
        }
        if (request.getPageInfo().getPageSize() == null || request.getPageInfo().getPageSize() == 0) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取参数失败:每页条数不能为空");
        }
        
        PageInfo<UsableBalanceVo> pageInfo = balanceQueryBusiSV.queryUsableBalance(request);
        UsableBalanceQueryResponse response = new UsableBalanceQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setPageInfo(pageInfo);
        response.setResponseHeader(responseHeader);
        LOG.info("可用余额（客户级别）分页查询结束");
        return response;
    }

}
