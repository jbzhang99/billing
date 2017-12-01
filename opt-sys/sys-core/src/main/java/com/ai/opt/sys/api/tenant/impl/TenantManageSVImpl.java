package com.ai.opt.sys.api.tenant.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.UUIDUtil;
import com.ai.opt.sys.api.tenant.interfaces.ITenantManageSV;
import com.ai.opt.sys.api.tenant.param.TenantInfoRequest;
import com.ai.opt.sys.api.tenant.param.TenantInfoResponse;
import com.ai.opt.sys.api.tenant.param.TenantInfoVo;
import com.ai.opt.sys.api.tenant.param.TenantInsertResponse;
import com.ai.opt.sys.api.tenant.param.TenantQueryResponse;
import com.ai.opt.sys.constants.SystemExceptCode;
import com.ai.opt.sys.constants.SysConstants.ResultCode;
import com.ai.opt.sys.constants.SysConstants.Tenant;
import com.ai.opt.sys.dao.mapper.bo.GnTenant;
import com.ai.opt.sys.service.business.interfaces.ITenantBusiSV;
import com.ai.opt.sys.service.business.interfaces.ITenantValidateSV;
import com.ai.opt.sys.service.validate.interfaces.IVoValidateSV;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class TenantManageSVImpl implements ITenantManageSV {

	@Autowired
	ITenantBusiSV itenantBusiSV;
	@Autowired
	IVoValidateSV iVoValidateSV;
	@Autowired
	ITenantValidateSV iTenantValidateSV;

	@Override
	public TenantQueryResponse queryTenantInfo(BaseInfo tenantRequest) throws BusinessException,SystemException {
		// 检查参数
		iVoValidateSV.validateQueryTenantInfo(tenantRequest);
		// 查询数据
		String tenantId = tenantRequest.getTenantId();
		GnTenant gnTenant = itenantBusiSV.queryByTenantId(tenantId);
		// 整理返回对象
		TenantQueryResponse tenantQueryResponse = new TenantQueryResponse();
		if (gnTenant != null) {
			BeanUtils.copyProperties(tenantQueryResponse, gnTenant);
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCode.SUCCESS_CODE, "查询成功");
		tenantQueryResponse.setResponseHeader(responseHeader);
		return tenantQueryResponse;
	}

	@Override
	public TenantInsertResponse insertTenantInfo(TenantInfoRequest tenantInfoRequest) throws BusinessException,SystemException {
		// 参数检查
		iVoValidateSV.validateInsertTenant(tenantInfoRequest);
		// 设置入参值 默认值
		GnTenant gnTenant = new GnTenant();
		BeanUtils.copyProperties(gnTenant, tenantInfoRequest);
		String tenantId = UUIDUtil.genId32();
		gnTenant.setTenantId(tenantId);
		gnTenant.setState(Tenant.STATE_NOTSIGNED);
		gnTenant.setCreateAccountId(tenantInfoRequest.getCreateAccountId());
		gnTenant.setCreateTime(DateUtil.getSysDate());
		/*GnAccount gnAccount = new GnAccount();
		gnAccount.setAccountId(tenantInfoRequest.getAccountId());
		gnAccount.setTenantId(tenantId);
		gnAccount.setUpdateAccountId(tenantInfoRequest.getUpdateAccountId());*/
		// 设置返回对象
		boolean isSuccess = itenantBusiSV.insertTenantAndSyncAccount(gnTenant);
		ResponseHeader responseHeader = new ResponseHeader();
		TenantInsertResponse tenantResponse = new TenantInsertResponse();
		if (isSuccess) {
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
			responseHeader.setResultMessage("数据库操作成功");
			tenantResponse.setTenantId(tenantId);
		} else {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ResultCode.FAIL_CODE);
			responseHeader.setResultMessage("数据库操作失败");
			tenantResponse.setTenantId(null);
		}
		tenantResponse.setResponseHeader(responseHeader);
		return tenantResponse;
	}

    @Override
    public BaseResponse updateTenant(TenantInfoRequest tenantInfoRequest)
            throws BusinessException, SystemException {
        // 参数检查
        iVoValidateSV.validateUpdateTenant(tenantInfoRequest);
        //校驗tenanntId是否存在
        iTenantValidateSV.checkTenantIdIsExit(tenantInfoRequest.getTenantId());
        // 设置入参值 默认值
        GnTenant gnTenant = new GnTenant();
        BeanUtils.copyProperties(gnTenant, tenantInfoRequest);
        gnTenant.setUpdateTime(DateUtil.getSysDate());
        gnTenant.setUpdateAccountId(tenantInfoRequest.getUpdateAccountId());
        int updateCount = itenantBusiSV.updateByTenantId(gnTenant);
        // 整理返回对象
        ResponseHeader responseHeader = new ResponseHeader();
        if (updateCount > 0) {
            responseHeader.setIsSuccess(true);
            responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
            responseHeader.setResultMessage("数据更新成功");
        } else {
            responseHeader.setIsSuccess(false);
            responseHeader.setResultCode(ResultCode.FAIL_CODE);
            responseHeader.setResultMessage("数据库更新失败");
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

	@Override
	public TenantInfoResponse queryTenantInfos(TenantInfoVo queryInfo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(queryInfo == null){
			 throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "参数对象为空");
		}
		List<GnTenant> queryList = itenantBusiSV.queryTenantInfos(queryInfo);
		TenantInfoResponse infoResponse = new TenantInfoResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		List<TenantInfoVo> list = new ArrayList<>();
		if(queryList!=null || queryList.size()>0){
			for(GnTenant vo : queryList){
				TenantInfoVo infoVo = new TenantInfoVo();
				BeanUtils.copyProperties(infoVo, vo);
				list.add(infoVo);
			}
		}
		infoResponse.setTenantInfoVo(list);
		responseHeader.setIsSuccess(true);
        responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
        responseHeader.setResultMessage("数据查询成功");
        infoResponse.setResponseHeader(responseHeader);
		return infoResponse;
	}

}
