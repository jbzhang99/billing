package com.ai.baas.amc.api.oweinfoquery.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.oweinfoquery.interfaces.IOweInfoQuerySV;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryResponse;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryResponse;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IOweInfoQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

/**
 * 欠费查询服务<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Component
public class OweInfoQuerySVImpl implements IOweInfoQuerySV {
    
    private static final Logger LOG = LogManager.getLogger(OweInfoQuerySVImpl.class);
    
    @Autowired
    private IOweInfoQueryBusiSV oweInfoQueryBusiSV;

    @Override
    public OweInfoListQueryResponse queryOweInfoList(OweInfoListQueryRequest request)
            throws BusinessException, SystemException {
        LOG.info("欠费分页查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "欠费分页查询请求不能为空");
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
        if (!StringUtil.isBlank(request.getStartDate()) && !DateUtil.isValidDate(request.getStartDate(), DateUtil.YYYYMM)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_TYPE_NOT_RIGHT, "传入的欠费开始时间格式有误["
                    + JSON.toJSONString(request.getStartDate()) + "]");
        }
        if (!StringUtil.isBlank(request.getEndDate()) && !DateUtil.isValidDate(request.getEndDate(), DateUtil.YYYYMM)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_TYPE_NOT_RIGHT, "传入的欠费月份结束区间格式有误["
                    + JSON.toJSONString(request.getEndDate()) + "]");
        }
        PageInfo<OweInfo> pageInfo = oweInfoQueryBusiSV.queryOweInfoList(request);
        OweInfoListQueryResponse response = new OweInfoListQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setPageInfo(pageInfo);
        response.setResponseHeader(responseHeader);
        LOG.info("欠费分页查询结束");
        return response;
    }

    @Override
    public OweDetailInfoQueryResponse queryOweDetailInfo(OweDetailInfoQueryRequest request)
            throws BusinessException, SystemException {
        LOG.info("欠费明细查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "欠费明细查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getCustId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "客户ID不能为空");
        }
        
        return oweInfoQueryBusiSV.queryOweDetailInfo(request);
    }

}
