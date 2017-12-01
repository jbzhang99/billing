package com.ai.baas.bmc.api.BaseInfo.impl;

import com.ai.opt.sdk.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.ChildeCodeResponse;
import com.ai.baas.bmc.api.baseInfo.params.QueryChildCodeRequest;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.service.business.interfaces.IBaseInfoBussiness;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation="true")
@Component
public class BaseInfoSVImpl implements IBaseInfoSV {

	@Autowired
	private IBaseInfoBussiness iBaseInfoBussiness;
	@Override
	public BaseCodeInfo getBaseInfo(QueryInfoParams param) {
		if(StringUtil.isBlank(param.getTenantId())){
			throw new SystemException("000001","tenantId不能为空");
		}
		if(StringUtil.isBlank(param.getParamType())){
			throw new SystemException("000002","paramType不能为空");
		}
		return iBaseInfoBussiness.getBaseInfo(param);
	}
	@Override
	public ChildeCodeResponse getChildCode(QueryChildCodeRequest request) throws BusinessException, SystemException {
		if(StringUtil.isBlank(request.getTenantId())){
			throw new SystemException("000001","tenantId不能为空");
		}
		return iBaseInfoBussiness.getChildCode(request);
	}

}
