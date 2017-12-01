package com.ai.opt.sys.api.gnrole.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sys.api.gnrole.param.DeleteRoleRequest;
import com.ai.opt.sys.api.gnrole.param.InsertRoleRequest;
import com.ai.opt.sys.api.gnrole.param.InsertRoleResponse;
import com.ai.opt.sys.api.gnrole.param.QueryRolePageRequest;
import com.ai.opt.sys.api.gnrole.param.QueryRolePageResponse;
import com.ai.opt.sys.api.gnrole.param.QueryRoleRequest;
import com.ai.opt.sys.api.gnrole.param.QueryRoleResponse;
import com.ai.opt.sys.api.gnrole.param.UpdateRoleRequest;

@Path("/rolemanage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IGnRoleManageSV {

	/**
     * 新增角色
     * @param insertRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL rolemanage/insertGnRoleInfo
     */
    @GET
    @Path("/insertGnRoleInfo")
	InsertRoleResponse insertGnRoleInfo(InsertRoleRequest insertRequest) throws BusinessException,SystemException;

    /**
     * 查询详情
     * @param queryRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL rolemanage/queryGnRoleInfo
     */
    @GET
    @Path("/queryGnRoleInfo")
	QueryRoleResponse queryGnRoleInfo(QueryRoleRequest queryRequest) throws BusinessException,SystemException;

    /**
     * 分页查询
     * @param queryRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL rolemanage/queryGnRolePageInfo
     */
    @GET
    @Path("/queryGnRolePageInfo")
	QueryRolePageResponse queryGnRolePageInfo(QueryRolePageRequest queryRequest) throws BusinessException,SystemException;

    /**
     * 修改
     * @param updateRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL rolemanage/updateGnRoleInfo
     */
    @GET
    @Path("/updateGnRoleInfo")
	BaseResponse updateGnRoleInfo(UpdateRoleRequest updateRequest) throws BusinessException,SystemException;

    /**
     * 删除
     * @param deleteRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL rolemanage/deleteGnRole
     */
    @GET
    @Path("/deleteGnRole")
	BaseResponse deleteGnRole(DeleteRoleRequest deleteRequest) throws BusinessException,SystemException;
}
