package com.ai.baas.pkgfee.api.feecal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.api.feecal.interfaces.IPackageFeeCalculateSV;
import com.ai.baas.pkgfee.api.feecal.params.ChangeConfigParam;
import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;
import com.ai.baas.pkgfee.api.feecal.params.RenewalParam;
import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.ai.baas.pkgfee.service.business.interfaces.IPackageFeeCalculateBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.alibaba.dubbo.config.annotation.Service;


@Service
@Component
public class PackageFeeCalculateSVImpl implements IPackageFeeCalculateSV {

	@Autowired
	IPackageFeeCalculateBusiSV packageFeeCalculateBusiSV;
	
	@Override
	public BaseResponse addFeeCal(FeeCalAddParam param) throws BusinessException, SystemException {
		System.out.println("order_id="+param.getOrderId()+",tenantId="+param.getTenantId());
		packageFeeCalculateBusiSV.addPackageFeeCal(param);
		ResponseHeader responseHeader = new ResponseHeader(true, CpPkgfeeConstants.SUCCESS, "成功");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse changeConfigFeeCal(ChangeConfigParam param) throws BusinessException, SystemException {
		packageFeeCalculateBusiSV.changeConfigPkgFeeCal(param);
		ResponseHeader responseHeader = new ResponseHeader(true, CpPkgfeeConstants.SUCCESS, "成功");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse renewalFeeCal(RenewalParam param) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
