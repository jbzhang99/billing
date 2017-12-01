package com.ai.baas.dst.api.couponcal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.couponcal.interfaces.ICouponCalSV;
import com.ai.baas.dst.api.couponcal.params.CouponCalRequest;
import com.ai.baas.dst.api.couponcal.params.CouponCalResponse;
import com.ai.baas.dst.api.couponcal.params.ProductReq;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.service.business.interfaces.ICouponCalBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.util.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 优惠卷计算服务
 * @author wangjing19
 */
@Service
@Component
public class CouponCalSVImpl implements ICouponCalSV {

	@Autowired
	private ICouponCalBusiSV iCouponCalBusiSV;
	
	@Override
	public CouponCalResponse getResultByCouponCal(CouponCalRequest request) {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(request.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if(StringUtil.isBlank(request.getCouponCode())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠卷code不能为空");
		}
		if(CollectionUtil.isEmpty(request.getProducts())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "产品列表不能为空");
		}
		for (ProductReq pro : request.getProducts()) {
			if (StringUtil.isBlank(pro.getProductID())) {
	            throw new BusinessException("产品ID为空");
	        }
			if ( (!StringUtil.isBlank(pro.getQuantity())) && Double.parseDouble(pro.getQuantity()) <= 0) {
	            throw new BusinessException("数量值错误");
	        }
	        if (StringUtil.isBlank(pro.getUnitPrice()) || Double.parseDouble(pro.getUnitPrice()) <= 0) {
	            throw new BusinessException("单价为空或值错误");
	        }
	        if (StringUtil.isBlank(pro.getTimeDuration()) || Double.parseDouble(pro.getTimeDuration()) <= 0) {
	            throw new BusinessException("时长为空或值错误");
	        }
		}
		
		return iCouponCalBusiSV.getResultByCouponCal(request);
	}
    
}
