package com.ai.baas.dst.api.billsdiscount.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.billsdiscount.interfaces.IBillDiscountMaintainSV;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountDeleteRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountMaintainResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.service.business.interfaces.IBillDiscountMaintainBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

/**
 * 账单优惠管理服务
 * @author wangluyang
 *
 */
@Service
@Component
public class BillDiscountMaintainSVImpl implements IBillDiscountMaintainSV {
    
    private static final Logger LOG = LogManager.getLogger(BillDiscountMaintainSVImpl.class);
    
    @Autowired
    private IBillDiscountMaintainBusiSV billDiscountMaintainBusiSV;

	@Override
	public BillDiscountMaintainResponse addBillDiscountProduct(BillDiscountVo vo)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("新增账单优惠产品开始");
        this.validateBillDiscountProduct(vo);
        String discountId = billDiscountMaintainBusiSV.addBillDiscountProduct(vo);
        BillDiscountMaintainResponse response = new BillDiscountMaintainResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setTradeSeq(vo.getTradeSeq());
        response.setTenantId(vo.getTenantId());
        response.setDiscountId(discountId);
        response.setResponseHeader(responseHeader);
        LOG.info("新增账单优惠产品成功");
        return response;
	}
    
	 /**
     * 参数校验
     * @param vo
     * @throws BusinessException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private void validateBillDiscountProduct(BillDiscountVo vo) throws BusinessException {
        if (vo == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠产品新增入参不能为空");
        }
        if (StringUtil.isBlank(vo.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(vo.getTradeSeq())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "消息流水不能为空");
        }
        if (StringUtil.isBlank(vo.getDiscountName())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠活动名称不能为空");
        }
        if (StringUtil.isBlank(vo.getDiscountType())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠类型不能为空");
        }
        List<String> calcTypeFieldValues = getFieldValues(DstConstants.DiscountInfo.CalcType.class);
        if(!calcTypeFieldValues.contains(vo.getDiscountType())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_WRONG, "账单优惠类型不合法");
        }
        if (StringUtil.isBlank(vo.getEffectDate())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "产品生效日期不能为空");
        }

        if (StringUtil.isBlank(vo.getExpireDate())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "产品失效日期不能为空");
        }

        if (!DateUtil.isValidDate(vo.getEffectDate(), DateUtil.DATETIME_FORMAT)) {
            throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "产品生效日期格式不正确["
                    + JSON.toJSONString(vo.getEffectDate()) + "]");
        }
        if (!DateUtil.isValidDate(vo.getExpireDate(), DateUtil.DATETIME_FORMAT)) {
            throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "产品失效日期格式不正确["
                    + JSON.toJSONString(vo.getExpireDate()) + "]");
        }
//        if (CollectionUtil.isEmpty(vo.getDiscountProductList())) {
//            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "参与优惠活动的产品列表不能为空");
//        }
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
	public BaseResponse deleteBillDiscountProduct(BillDiscountDeleteRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("删除账单优惠产品开始");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "删除指定账单优惠活动产品请求不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getDiscountId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠产品ID不能为空");
        }
        billDiscountMaintainBusiSV.deleteBillDiscountProduct(request);
        BaseResponse response = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        LOG.info("删除账单优惠产品成功");
        return response;
	}

	@Override
	public BaseResponse updateBillDiscountProduct(BillDiscountUpdateVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("更新账单优惠产品开始");
        if (vo == null) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠产品变更入参不能为空");
        }
        if (StringUtil.isBlank(vo.getTenantId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(vo.getDiscountId())) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "账单优惠产品ID不能为空");
        }
        if (!StringUtil.isBlank(vo.getEffectDate()) && !DateUtil.isValidDate(vo.getEffectDate(), DateUtil.DATETIME_FORMAT)) {
            throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "产品生效日期格式不正确["
                    + JSON.toJSONString(vo.getEffectDate()) + "]");
        }
        if (!StringUtil.isBlank(vo.getExpireDate()) && !DateUtil.isValidDate(vo.getExpireDate(), DateUtil.DATETIME_FORMAT)) {
            throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "产品失效日期格式不正确["
                    + JSON.toJSONString(vo.getExpireDate()) + "]");
        }
        String status = vo.getStatus();
        if (!StringUtil.isBlank(status)
                && !DstConstants.DiscountInfo.Status.INVALID.equals(status)
                && !DstConstants.DiscountInfo.Status.EFFECTIVE.equals(status)
                && !DstConstants.DiscountInfo.Status.TO_BE_EFFECTIVE.equals(status)) {
            throw new BusinessException(ExceptCodeConstant.PARAM_IS_WRONG, "账单优惠活动产品变更状态有误");
        }
        billDiscountMaintainBusiSV.updateBillDiscountProduct(vo);
        BaseResponse response = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        LOG.info("更新账单优惠产品成功");
        return response;
	}
}
