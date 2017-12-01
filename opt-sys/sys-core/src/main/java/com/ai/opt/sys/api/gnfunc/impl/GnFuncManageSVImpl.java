package com.ai.opt.sys.api.gnfunc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sys.api.gnfunc.interfaces.IGnFuncManageSV;
import com.ai.opt.sys.api.gnfunc.param.DeleteFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.InsertGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.InsertGnFuncResponse;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.GnFuncData;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageResponse;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncResponse;
import com.ai.opt.sys.api.gnfunc.param.QuerySonGnFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.QuerySonGnFuncResponse;
import com.ai.opt.sys.api.gnfunc.param.UpdateGnFuncRequest;
import com.ai.opt.sys.constants.SysConstants;
import com.ai.opt.sys.constants.SysConstants.ResultCode;
import com.ai.opt.sys.dao.mapper.bo.GnFunc;
import com.ai.opt.sys.service.business.interfaces.IGnFuncBusiSV;
import com.ai.opt.sys.service.validate.interfaces.IVoValidateSV;
import com.ai.opt.sys.util.SystemSeqUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
@Component
public class GnFuncManageSVImpl implements IGnFuncManageSV {
	@Autowired
	IGnFuncBusiSV iGnFuncBusiSV;
	@Autowired 
	IVoValidateSV iVoValidateSV;

	@Override
	public QueryGnFuncPageResponse queryFuncPageInfo(QueryGnFuncPageRequest request) throws BusinessException, SystemException {
		iVoValidateSV.validateQueryFuncPageInfo(request);
		QueryGnFuncPageResponse gnFuncPageResponse = new QueryGnFuncPageResponse();
		PageInfo<GnFuncData> pageInfo=new PageInfo<GnFuncData>();
		PageInfo<GnFunc> pageVo = iGnFuncBusiSV.queryFuncInfo(request);
		pageInfo.setCount(pageVo.getCount());
		pageInfo.setPageNo(pageVo.getPageNo());
		pageInfo.setPageSize(pageVo.getPageSize());
		List<GnFunc> result = pageVo.getResult();
		if (result != null && result.size()>0) {
			Gson gson = new Gson();
			String funcJson = gson.toJson(result);
			List<GnFuncData> pageDataList = gson.fromJson(funcJson, new TypeToken<List<GnFuncData>>() {
			}.getType());
			pageInfo.setResult(pageDataList);
		}
		gnFuncPageResponse.setPageInfo(pageInfo);
		ResponseHeader responseHeader=new ResponseHeader(true, ResultCode.SUCCESS_CODE,"查询成功");
		gnFuncPageResponse.setResponseHeader(responseHeader);
		return gnFuncPageResponse;
	}

	@Override
	public QueryGnFuncResponse queryFuncInfo(QueryGnFuncInfoRequest queryRequest) throws BusinessException, SystemException {
		iVoValidateSV.validateQueryFuncInfo(queryRequest);
		GnFunc gnTenant = iGnFuncBusiSV.queryFuncInfo(queryRequest);
		// 整理返回对象
		QueryGnFuncResponse tenantQueryResponse = new QueryGnFuncResponse();
		if (gnTenant != null) {
			BeanUtils.copyProperties(tenantQueryResponse, gnTenant);
			ResponseHeader responseHeader = new ResponseHeader(true, ResultCode.SUCCESS_CODE, "查询成功");
			tenantQueryResponse.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader = new ResponseHeader(true, ResultCode.FAIL_CODE, "查询失败，无数据");
			tenantQueryResponse.setResponseHeader(responseHeader);
		}
		return tenantQueryResponse;
	}

	@Override
	public BaseResponse updateFuncById(UpdateGnFuncRequest request) throws BusinessException, SystemException {
		iVoValidateSV.validateUpdateFuncById(request);
		// 数据库操作
		GnFunc gnFunc = new GnFunc();
		BeanUtils.copyProperties(gnFunc, request);
		gnFunc.setUpdateTime(DateUtil.getSysDate());
		int updateCount = iGnFuncBusiSV.updateFuncById(gnFunc);
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
	public InsertGnFuncResponse insertFuncInfo(InsertGnFuncInfoRequest request) throws BusinessException, SystemException {
		iVoValidateSV.validateInsertFuncInfo(request);
		// 设置默认值
		GnFunc func = new GnFunc();
		BeanUtils.copyProperties(func, request);
		func.setState(SysConstants.GnFunc.FUNC_NORMAL_STATE);
		if (func.getActiveTime() == null) {
			func.setActiveTime(DateUtil.getSysDate());
		}
		if (func.getInactiveTime() == null) {
			func.setInactiveTime(DateUtil.getTimestamp(SysConstants.GnFunc.INACTIVE_DATE, DateUtil.DATETIME_FORMAT));
		}
		func.setCreateTime(DateUtil.getSysDate());
		long funcId = SystemSeqUtil.createFuncId();
		func.setFuncId(funcId);
		int count = iGnFuncBusiSV.insertFunc(func);
		// 整理返回对象
		InsertGnFuncResponse response = new InsertGnFuncResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		if (count > 0) {
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
			responseHeader.setResultMessage("数据库操作成功");
			response.setFuncId(funcId);
		} else {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ResultCode.FAIL_CODE);
			responseHeader.setResultMessage("数据库操作失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse deleteFuncById(DeleteFuncRequest deletRequest) {
		iVoValidateSV.validateDeleteFuncById(deletRequest);
		int count = iGnFuncBusiSV.deleteFuncById(deletRequest.getFuncId());
		// 整理返回对象
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		if (count > 0) {
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
			responseHeader.setResultMessage("数据库操作成功");
		} else {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ResultCode.FAIL_CODE);
			responseHeader.setResultMessage("数据库操作失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public QuerySonGnFuncResponse querySonGnFuncInfo(QuerySonGnFuncRequest queryRequest) throws BusinessException, SystemException {
		iVoValidateSV.validateQuerySonGnFuncInfo(queryRequest);
		List<GnFunc> funcList = iGnFuncBusiSV.queryChilderFuncInfo(queryRequest.getFuncId());
		QuerySonGnFuncResponse sonGnFuncResponse = new QuerySonGnFuncResponse();
		if(funcList != null && funcList.size()>0){
			Gson gson = new Gson();
			List<GnFuncData> funcDataList =  gson.fromJson(gson.toJson(funcList),new TypeToken<List<GnFuncData>>() {
			}.getType());
			sonGnFuncResponse.setFuncDataList(funcDataList);
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCode.SUCCESS_CODE, "查询成功");
		sonGnFuncResponse.setResponseHeader(responseHeader );
		return sonGnFuncResponse;
	}

}
