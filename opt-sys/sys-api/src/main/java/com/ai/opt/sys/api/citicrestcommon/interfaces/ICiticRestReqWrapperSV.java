package com.ai.opt.sys.api.citicrestcommon.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.TotalBillQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.TotalBillQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 中信云公共使用的rest服务包装.<br>
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/citic")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface ICiticRestReqWrapperSV {

    /**
     * 用户查询管理
     * @param queryVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL citic/searchUser
     */
    @GET
    @Path("/searchUser")
    public UserQueryResponse searchUser(UserInfoQueryVo queryVo) throws BusinessException,SystemException;

    /**
     * 机构查询管理
     * @param queryVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL citic/searchOrg
     */
    @GET
    @Path("/searchOrg")
    public OrgQueryResponse searchOrg(OrgQueryVo queryVo) throws BusinessException,SystemException;
}
