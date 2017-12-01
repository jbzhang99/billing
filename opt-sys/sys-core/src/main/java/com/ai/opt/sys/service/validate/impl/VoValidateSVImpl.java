package com.ai.opt.sys.service.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.sys.api.gnfunc.param.DeleteFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.InsertGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.api.gnfunc.param.QuerySonGnFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.UpdateGnFuncRequest;
import com.ai.opt.sys.api.tenant.param.TenantInfoRequest;
import com.ai.opt.sys.constants.SystemExceptCode;
import com.ai.opt.sys.service.business.interfaces.ITenantValidateSV;
import com.ai.opt.sys.service.validate.interfaces.IGnFuncValidateSV;
import com.ai.opt.sys.service.validate.interfaces.IVoValidateSV;
import com.ai.paas.ipaas.util.StringUtil;

@Component
public class VoValidateSVImpl implements IVoValidateSV {
	@Autowired
	IGnFuncValidateSV iGnFuncValidateSV;
	@Autowired
    ITenantValidateSV iTenantValidateSV;
	@Override
	public void validateQueryFuncPageInfo(QueryGnFuncPageRequest request) throws BusinessException {
		if (request == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
		iGnFuncValidateSV.checkPageNo(request.getPageNo());
		iGnFuncValidateSV.checkPageSize(request.getPageSize());
	}
	@Override
	public void validateQueryFuncInfo(QueryGnFuncInfoRequest queryRequest) throws BusinessException {
		if (queryRequest == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
		iGnFuncValidateSV.checkFuncId(queryRequest.getFuncId());
	}
	@Override
	public void validateUpdateFuncById(UpdateGnFuncRequest request) throws BusinessException {
		if (request == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
		iGnFuncValidateSV.checkFuncId(request.getFuncId());
		String funcName = request.getFuncName();
		if(!StringUtil.isBlank(funcName)){
			iGnFuncValidateSV.checkFuncName(funcName);
		}
		String funcUrl = request.getFuncUrl();
		if(!StringUtil.isBlank(funcUrl)){
			iGnFuncValidateSV.checkFuncUrl(funcUrl);
		}
		iGnFuncValidateSV.checkUpdateAccountId(request.getUpdateAccountId());
	}
	@Override
	public void validateInsertFuncInfo(InsertGnFuncInfoRequest request) throws BusinessException {
		if (request == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
		iGnFuncValidateSV.checkFuncName(request.getFuncName());
		iGnFuncValidateSV.checkFuncType(request.getFuncType());
		iGnFuncValidateSV.checkFuncUrl(request.getFuncUrl());
	}
	@Override
	public void validateDeleteFuncById(DeleteFuncRequest deletRequest) throws BusinessException {
		if (deletRequest == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
		iGnFuncValidateSV.checkFuncId(deletRequest.getFuncId());
	}
	@Override
	public void validateQuerySonGnFuncInfo(QuerySonGnFuncRequest queryRequest) throws BusinessException {
		if (queryRequest == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
		iGnFuncValidateSV.checkFuncId(queryRequest.getFuncId());
	}
	@Override
	public void validateQueyIndustry(String code) {
		 if (StringUtil.isBlank(code)) {
	            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
	        }
		
	}
	 @Override
    public void validateQueryTenantInfo(BaseInfo tenantRequest) throws BusinessException {
        if (tenantRequest == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
        iTenantValidateSV.checkTenantId(tenantRequest.getTenantId());
    }
	@Override
	public void validateInsertTenant(TenantInfoRequest tenantInfoRequest) {
		if (tenantInfoRequest == null) {
            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
        }
       // iTenantValidateSV.checkAccountId(tenantInfoRequest.getAccountId());
        iTenantValidateSV.checkTenantName(tenantInfoRequest.getTenantName());
        iTenantValidateSV.checkCreateAccountId(tenantInfoRequest.getCreateAccountId());
		
	}
	@Override
	public void validateUpdateTenant(TenantInfoRequest request) {
		 if (request==null) {
	            throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
	        }
	        iTenantValidateSV.checkTenantId(request.getTenantId());
	        iTenantValidateSV.checkUpdateAccountId(request.getUpdateAccountId());
		
	}
}
