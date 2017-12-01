package com.ai.opt.sys.api.gnfunc.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sys.api.gnfunc.param.DeleteFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.InsertGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.InsertGnFuncResponse;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageResponse;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncResponse;
import com.ai.opt.sys.api.gnfunc.param.QuerySonGnFuncRequest;
import com.ai.opt.sys.api.gnfunc.param.QuerySonGnFuncResponse;
import com.ai.opt.sys.api.gnfunc.param.UpdateGnFuncRequest;

/**
 * 功能服务 Date: 2016年4月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
@Path("/funcmanage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IGnFuncManageSV {
	
	/**
     * 服务功能分页查询
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL funcmanage/queryFuncPageInfo
     */
    @GET
    @Path("/queryFuncPageInfo")
	QueryGnFuncPageResponse queryFuncPageInfo(QueryGnFuncPageRequest request) throws BusinessException, SystemException;

	
    /**
     * 根据id查询
     * @param queryRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL funcmanage/queryFuncInfo
     */
    @GET
    @Path("/queryFuncInfo")
	QueryGnFuncResponse queryFuncInfo(QueryGnFuncInfoRequest queryRequest) throws BusinessException, SystemException;

    /**
     * 修改功能服务
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL funcmanage/updateFuncById
     */
    @GET
    @Path("/updateFuncById")
	BaseResponse updateFuncById(UpdateGnFuncRequest request) throws BusinessException, SystemException;

	
    /**
     * 添加功能服务
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL funcmanage/insertFuncInfo
     */
    @GET
    @Path("/insertFuncInfo")
	InsertGnFuncResponse insertFuncInfo(InsertGnFuncInfoRequest request) throws BusinessException, SystemException;

	
    /**
     * 删除功能服务
     * @param deletRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL funcmanage/deleteFuncById
     */
    @GET
    @Path("/deleteFuncById")
	BaseResponse deleteFuncById(DeleteFuncRequest deletRequest) throws BusinessException, SystemException;
	
    /**
     * 查询子功能
     * @param queryRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL funcmanage/querySonGnFuncInfo
     */
    @GET
    @Path("/querySonGnFuncInfo")
	QuerySonGnFuncResponse querySonGnFuncInfo(QuerySonGnFuncRequest queryRequest) throws BusinessException, SystemException;
}
