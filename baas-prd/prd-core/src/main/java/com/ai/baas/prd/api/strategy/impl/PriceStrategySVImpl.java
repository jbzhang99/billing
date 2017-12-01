package com.ai.baas.prd.api.strategy.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.strategy.interfaces.IPriceStrategySV;
import com.ai.baas.prd.api.strategy.params.CheckPolicyParam;
import com.ai.baas.prd.api.strategy.params.DeleteStrategyParam;
import com.ai.baas.prd.api.strategy.params.PriceStrategyQueryReponse;
import com.ai.baas.prd.api.strategy.params.QueryDetailParam;
import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.api.strategy.params.StrategyAddParams;
import com.ai.baas.prd.api.strategy.params.StrategyDetailQueryReponse;
import com.ai.baas.prd.constants.ExceptCodeConstants;
import com.ai.baas.prd.service.business.interfaces.IPriceStrategyBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 定价策略
 * @author wangluyang
 *
 */
@Service
@Component
public class PriceStrategySVImpl implements IPriceStrategySV {

	@Autowired
	private IPriceStrategyBusiSV priceStrategyBusiSV;
	
	@Override
	public BaseResponse addStrategy(StrategyAddParams vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		BaseResponse baseResponse = new BaseResponse();
		if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getPolicyName())){
        	 throw new BusinessException("策略名称不能为空");
        }
        if(vo.getPolicyVo()==null){
             throw new BusinessException("定价策略详情不能为空");
        }
        
        priceStrategyBusiSV.addStrategy(vo);
        
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public PriceStrategyQueryReponse queryStrategy(QueryParams params) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(params.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
		
		return this.priceStrategyBusiSV.queryStrategy(params);
	}

	@Override
	public StrategyDetailQueryReponse getStrategyDetail(QueryDetailParam params)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(params.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
		if(StringUtil.isBlank(params.getPolicyId())){
            throw new BusinessException("策略id不能为空");
        }
		return this.priceStrategyBusiSV.getStrategyDetail(params);
	}

	@Override
	public BaseResponse deleteStrategy(DeleteStrategyParam vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
		if(StringUtil.isBlank(vo.getPolicyId())){
            throw new BusinessException("策略id不能为空");
        }
		return this.priceStrategyBusiSV.deleteStrategy(vo);
	}

	@Override
	public BaseResponse checkExistPolicyName(CheckPolicyParam vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
		if(StringUtil.isBlank(vo.getPolicyName())){
            throw new BusinessException("策略名称不能为空");
        }
		return this.priceStrategyBusiSV.checkExistPolicyName(vo);
	}

}
