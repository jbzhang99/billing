package com.ai.baas.dst.api.discountcal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.discountcal.interfaces.IDiscountCalSV;
import com.ai.baas.dst.api.discountcal.params.DiscountCalRequest;
import com.ai.baas.dst.api.discountcal.params.DiscountCalResponse;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.service.business.interfaces.IDiscountCalBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.util.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 优惠活动计算服务
 * @author wangjing19
 */
@Service
@Component
public class DiscountCalSVImpl implements IDiscountCalSV {

	@Autowired
	private IDiscountCalBusiSV iDiscountCalBusiSV;
	
	@Override
	public DiscountCalResponse getResultByDiscountCal(DiscountCalRequest request) {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(request.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if(CollectionUtil.isEmpty(request.getPriceProductIDs())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "定价产品ID列表不能为空");
		}
		if(StringUtil.isBlank(request.getUnitPrice())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "单价不能为空");
		}
		if(Double.valueOf(request.getUnitPrice()) < 0 ){
			throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "单价值错误");
		}
		if(StringUtil.isBlank(request.getQuantity())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "数量不能为空");
		}
		if(Double.valueOf(request.getQuantity()) < 0){
			throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "数量值错误");
		}
		if(!StringUtil.isBlank(request.getAnoQuantity()) && Double.valueOf(request.getAnoQuantity()) <= 0){
			throw new BusinessException(ExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "第二个数量值错误");
		}
		
		return iDiscountCalBusiSV.getResultByDiscountCal(request);
	}
    
	
   
}
