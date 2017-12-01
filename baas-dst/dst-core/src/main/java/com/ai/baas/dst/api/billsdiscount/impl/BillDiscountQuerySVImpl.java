package com.ai.baas.dst.api.billsdiscount.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.billsdiscount.interfaces.IBillDiscountQuerySV;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountInfo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryResponse;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.service.business.interfaces.IBillDiscountQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

/**
 * 账单优惠产品查询服务
 * @author wangluyang
 *
 */
@Service
@Component
public class BillDiscountQuerySVImpl implements IBillDiscountQuerySV {
    
    private static final Logger LOG = LogManager.getLogger(BillDiscountQuerySVImpl.class);
    
    @Autowired
    private IBillDiscountQueryBusiSV billDiscountQueryBusiSV;

    public BillDiscountListQueryResponse queryBillDiscountProductList(
    		BillDiscountListQueryRequest request) throws BusinessException, SystemException {
        LOG.info("账单优惠活动产品列表分页查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠活动产品列表查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (request.getPageInfo() == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "获取参数失败:分页信息不能为空");
        }
        if (request.getPageInfo().getPageNo() == null || request.getPageInfo().getPageNo() == 0) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "获取参数失败:查询页码不能为空");
        }
        if (request.getPageInfo().getPageSize() == null || request.getPageInfo().getPageSize() == 0) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "获取参数失败:每页条数不能为空");
        }
        if(!StringUtil.isBlank(request.getDiscountType())) {
            List<String> calcTypeFieldValues = getFieldValues(DstConstants.DiscountInfo.CalcType.class);
            if(!calcTypeFieldValues.contains(request.getDiscountType())) {
                throw new BusinessException(ExceptCodeConstant.PARAM_IS_WRONG, "账单优惠类型不合法");
            }
        }
        PageInfo<BillDiscountInfo> pageInfo = billDiscountQueryBusiSV.queryBillDiscountList(request);
      
        BillDiscountListQueryResponse response = new BillDiscountListQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setPageInfo(pageInfo);
        response.setResponseHeader(responseHeader);
        LOG.info("账单优惠活动产品列表分页查询结束"+JSONObject.toJSONString(response));
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

	@Override
	public BillDiscountQueryResponse queryBillDiscountProduct(BillDiscountQueryRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("账单优惠活动产品查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠活动产品查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getDiscountId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠产品ID不能为空");
        }
        BillDiscountInfo info = billDiscountQueryBusiSV.queryBillDiscountProduct(request);
        BillDiscountQueryResponse response = new BillDiscountQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        response.setInfo(info);
        LOG.info("账单优惠活动产品查询结束");
        return response;
	}

	@Override
	public EffectiveProductQueryResponse queryEffectiveScopeProduct(EffectiveProductQueryRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("生效产品查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getEffectDate())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "生效日期不能为空");
        }
        if (StringUtil.isBlank(request.getExpireDate())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "失效日期不能为空");
        }
        List<String> productIds = billDiscountQueryBusiSV.queryEffectiveScopeProduct(request);
        EffectiveProductQueryResponse response = new EffectiveProductQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        response.setProductIds(productIds);;
        LOG.info("生效产品查询结束");
		return response;
	}

	@Override
	public EffectiveProductQueryResponse queryEffectiveCoupon(BaseInfo request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("生效产品查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        List<String> productIds = billDiscountQueryBusiSV.queryEffectiveCoupon(request);
        EffectiveProductQueryResponse response = new EffectiveProductQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        response.setProductIds(productIds);;
        LOG.info("生效产品查询结束");
		return response;
	}

	@Override
	public BillDiscountListResponse queryDiscountList(BillDiscountListQueryRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("账单优惠活动产品列表查询开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠活动产品列表查询请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if(!StringUtil.isBlank(request.getDiscountType())) {
            List<String> calcTypeFieldValues = getFieldValues(DstConstants.DiscountInfo.CalcType.class);
            if(!calcTypeFieldValues.contains(request.getDiscountType())) {
                throw new BusinessException(ExceptCodeConstant.PARAM_IS_WRONG, "账单优惠类型不合法");
            }
        }
        List<BillDiscountInfo> discountInfos = billDiscountQueryBusiSV.queryDiscountList(request);
        BillDiscountListResponse response = new BillDiscountListResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setDiscounts(discountInfos);
        response.setResponseHeader(responseHeader);
        LOG.info("账单优惠活动产品列表分页查询结束");
        return response;
	}
}
