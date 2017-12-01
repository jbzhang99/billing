package com.ai.baas.prd.api.pricemaking.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.pricemaking.interfaces.IPriceMakingSV;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;
import com.ai.baas.prd.constants.ExceptCodeConstants;
import com.ai.baas.prd.service.business.interfaces.IPriceMakingBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class PriceMakingSVImpl implements IPriceMakingSV {
	
	@Autowired
	IPriceMakingBusiSV priceMakingBusiSV;
	
	@Override
	public BaseResponse addPriceMaking(PriceMakingAddParam param)
			throws BusinessException, SystemException {
		verifyRequestParam(param);
		priceMakingBusiSV.addPriceMaking(param);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse modifyPriceMaking(PriceMakingAddParam param)
			throws BusinessException, SystemException {
		verifyRequestParam(param);
		priceMakingBusiSV.modifyPriceMaking(param);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse deletePriceMaking(PriceMakingDelParam param) throws BusinessException,
			SystemException {
		priceMakingBusiSV.deletePriceMaking(param);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	
	private void verifyRequestParam(PriceMakingAddParam param) throws BusinessException{
		if(StringUtils.isBlank(param.getTenantId())){
			throw new BusinessException("租户ID不能为空!");
		}
		if(StringUtils.isBlank(param.getMainProductId())){
			throw new BusinessException("主产品ID不能为空!");
		}
		if(CollectionUtil.isEmpty(param.getPriceFactorList())){
			throw new BusinessException("价格因子列表不能为空!");
		}
	}
	
	
}
