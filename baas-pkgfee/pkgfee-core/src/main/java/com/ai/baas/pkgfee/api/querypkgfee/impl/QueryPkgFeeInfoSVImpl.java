package com.ai.baas.pkgfee.api.querypkgfee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.api.querypkgfee.interfaces.IQueryPkgFeeInfoSV;
import com.ai.baas.pkgfee.api.querypkgfee.params.PkgfeeQueryRequest;
import com.ai.baas.pkgfee.service.business.interfaces.*;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
@Service
@Component
public class QueryPkgFeeInfoSVImpl implements IQueryPkgFeeInfoSV{
	
	@Autowired
	private IQueryPkgFeeBusi iQueryPkgFeeBusi;
	
	@Override
	public BaseResponse getPkgFeeQueInfo(PkgfeeQueryRequest pkgFeeQueryRequest)
			throws BusinessException, SystemException {
		if(StringUtil.isBlank(pkgFeeQueryRequest.getTenantId())){
			throw new BusinessException("查询的TenantId不可为空");
		}
		
		// TODO Auto-generated method stub
		BaseResponse response = iQueryPkgFeeBusi.GetPkgfeeQueInfo(pkgFeeQueryRequest.getTenantId());
		return response;
	}

}
