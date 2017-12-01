package com.ai.baas.pkgfee.api.pkgunsubscribe.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.api.pkgunsubscribe.interfaces.IPkgUnsubscribeSV;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeRequest;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeResponse;
import com.ai.baas.pkgfee.service.business.interfaces.IPkgUnsubscribeBusi;
import com.ai.baas.pkgfee.util.DateUtils;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class PkgUnsubscribeSVImpl implements IPkgUnsubscribeSV{
	
	@Autowired
	private IPkgUnsubscribeBusi iPkgUnsubscribeBusi;
	
	@Override
	public PkgUnsubscribeResponse unsubscribe(PkgUnsubscribeRequest request)
			throws BusinessException, SystemException {
		if(StringUtil.isBlank(request.getTenantId())){
			throw new BusinessException("tenantId不可为空");
		}
		if(StringUtil.isBlank(request.getInstanceId())){
			throw new BusinessException("instanceId不可为空");
		}
		if(StringUtil.isBlank(request.getPriceCode())){
			throw new BusinessException("priceCode不可为空");
		}
		if(StringUtil.isBlank(request.getUnsubTime())){
			throw new BusinessException("unsubTime不可为空");
		}
		DateUtils.verifyTextTime(request.getUnsubTime());
		if(StringUtil.isBlank(request.getExtCustId())){
			throw new BusinessException("custId不可为空");
		}
		
		// TODO Auto-generated method stub
		PkgUnsubscribeResponse response = iPkgUnsubscribeBusi.pkgUnsubscribe(request);
		return response;
	}

}
