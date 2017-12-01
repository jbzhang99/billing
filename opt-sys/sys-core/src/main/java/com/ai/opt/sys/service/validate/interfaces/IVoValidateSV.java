package com.ai.opt.sys.service.validate.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.sys.api.gnfunc.param.DeleteFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.InsertGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.api.gnfunc.param.QuerySonGnFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.UpdateGnFuncRequest;
import com.ai.opt.sys.api.tenant.param.TenantInfoRequest;


public interface IVoValidateSV {
	/**
	 * 功能服务分页查询参数检查
	 * @param request
	 * @throws BusinessException
	 */
	void validateQueryFuncPageInfo(QueryGnFuncPageRequest request) throws BusinessException;
	
	/**
	 * 功能服务详情查询参数检查
	 * @param queryRequest
	 * @throws BusinessException
	 */
	void validateQueryFuncInfo(QueryGnFuncInfoRequest queryRequest) throws BusinessException;
	
	/**
	 * 更新功能参数检查
	 * @param request
	 * @throws BusinessException
	 */
	void validateUpdateFuncById(UpdateGnFuncRequest request) throws BusinessException;
	
	/**
	 * 新增功能参数检查
	 * @param request
	 * @throws BusinessException
	 */
	void validateInsertFuncInfo(InsertGnFuncInfoRequest request) throws BusinessException;
	
	/**
	 * 删除功能参数检查
	 * @param deletRequest
	 * @throws BusinessException
	 */
	void validateDeleteFuncById(DeleteFuncRequest deletRequest) throws BusinessException;
	
	/**
	 * 查找子功能参数检查
	 * @param queryRequest
	 * @throws BusinessException
	 */
	void validateQuerySonGnFuncInfo(QuerySonGnFuncRequest queryRequest) throws BusinessException;

	void validateQueyIndustry(String code);

	void validateQueryTenantInfo(BaseInfo tenantRequest);

	void validateInsertTenant(TenantInfoRequest tenantInfoRequest);

	void validateUpdateTenant(TenantInfoRequest tenantInfoRequest);
}
