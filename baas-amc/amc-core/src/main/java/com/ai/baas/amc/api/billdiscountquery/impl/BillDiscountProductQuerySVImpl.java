package com.ai.baas.amc.api.billdiscountquery.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.billdiscountquery.interfaces.IBillDiscountProductQuerySV;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductInfo;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryResponse;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryResponse;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IBillDiscountProductQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 账单优惠产品查询服务<br>
 * 
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author fanpw
 */
@Service
@Component
public class BillDiscountProductQuerySVImpl implements IBillDiscountProductQuerySV {
    
    private static final Logger LOG = LogManager.getLogger(BillDiscountProductQuerySVImpl.class);
    
    @Autowired
    private IBillDiscountProductQueryBusiSV billDiscountProductQueryBusiSV;

    public BillDiscountProductListQueryResponse queryBillDiscountProductList(
            BillDiscountProductListQueryRequest request) throws BusinessException, SystemException {
        LOG.info("账单优惠活动产品列表分页查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账单优惠活动产品列表查询请求不能为空");
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
        if(!StringUtil.isBlank(request.getDiscountType())) {
            List<String> calcTypeFieldValues = getFieldValues(AmcConstants.AmcProductInfo.CalcType.class);
            if(!calcTypeFieldValues.contains(request.getDiscountType())) {
                throw new BusinessException(ExceptCodeConstants.PARAM_IS_WRONG, "账单优惠类型不合法");
            }
        }
        PageInfo<BillDiscountProductInfo> pageInfo = billDiscountProductQueryBusiSV.queryBillDiscountProductList(request);
        BillDiscountProductListQueryResponse response = new BillDiscountProductListQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setPageInfo(pageInfo);
        response.setResponseHeader(responseHeader);
        LOG.info("账单优惠活动产品列表分页查询结束");
        return response;
    }

    public BillDiscountProductQueryResponse queryBillDiscountProduct(
            BillDiscountProductQueryRequest request) throws BusinessException, SystemException {
        LOG.info("账单优惠活动产品查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账单优惠活动产品查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getProductId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账单优惠产品ID不能为空");
        }
        BillDiscountProductInfo info = billDiscountProductQueryBusiSV.queryBillDiscountProduct(request);
        BillDiscountProductQueryResponse response = new BillDiscountProductQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        response.setInfo(info);
        LOG.info("账单优惠活动产品查询结束");
        return response;
    }

    public static List<String> getFieldValues(Class<?> t) throws SystemException {
        List<String> list = new ArrayList<String>();
        Field[] fields = t.getFields();
        for (Field field : fields) {
            try {
                list.add(field.get(t).toString());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new SystemException(e);
            }
        }
        return list;
    }
}
