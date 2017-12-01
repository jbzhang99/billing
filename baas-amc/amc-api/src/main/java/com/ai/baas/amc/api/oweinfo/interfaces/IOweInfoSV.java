package com.ai.baas.amc.api.oweinfo.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.oweinfo.params.OweInfoCreateRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/oweinfo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IOweInfoSV {
    /**
     * 生成欠费信息
     * 
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author mayt
     * @ApiDocMethod
     * @RestRelativeURL oweinfo/createOweInfo
     */
    @POST
    @Path("/createOweInfo")
    BaseResponse createOweInfo(OweInfoCreateRequest request) throws BusinessException,
            SystemException;
}
