package com.ai.baas.cust.api.custinfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.cust.api.custinfo.interfaces.ICustInfoQuerySV;
import com.ai.baas.cust.api.custinfo.params.CustInfoResponse;
import com.ai.baas.cust.api.custinfo.params.QueryCustInfoRequest;
import com.ai.baas.cust.service.business.interfaces.ICustInfoQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class CustInfoQuerySVImpl implements ICustInfoQuerySV {

	@Autowired
	ICustInfoQueryBusiSV ICustInfoQueryBusiSV;
	@Override
	public CustInfoResponse getCustInfos(QueryCustInfoRequest param) throws BusinessException, SystemException{
		//需要添加租户id，证件号码等非空条件验证
		if(StringUtil.isBlank(param.getTenantId())){
			throw new BusinessException("888888", "[租户Id]不能为空");
		}
		
		return ICustInfoQueryBusiSV.getCustInfos(param);
	}

}
