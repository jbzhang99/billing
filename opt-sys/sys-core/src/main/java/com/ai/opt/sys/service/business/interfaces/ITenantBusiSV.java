package com.ai.opt.sys.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.api.tenant.param.TenantInfoVo;
import com.ai.opt.sys.dao.mapper.bo.GnTenant;

public interface ITenantBusiSV {
	GnTenant queryByTenantId(String tenantId) throws SystemException;
	boolean insertTenantAndSyncAccount(GnTenant gnTenant) throws SystemException;
	int updateByTenantId(GnTenant gnTenant) throws SystemException;
	List<GnTenant> queryTenantInfos(TenantInfoVo queryInfo) throws SystemException;
}
