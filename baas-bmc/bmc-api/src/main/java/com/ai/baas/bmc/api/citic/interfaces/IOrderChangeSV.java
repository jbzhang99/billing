package com.ai.baas.bmc.api.citic.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.bmc.api.citic.params.InstanceInfo;
import com.ai.baas.bmc.api.citic.params.InstanceUpdateInfo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/citic")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IOrderChangeSV {

    /**
     * 实例修改接口-中信
     * 
     * @param instanceUpdateInfo
     * @return
     * @author wk
     * @ApiDocMethod BaaS-0100
     * @RestRelativeURL citic/instanceChange
     */
    @POST
    @Path("/instanceChange")
     public BaseResponse instanceChange(InstanceUpdateInfo instanceUpdateInfo) throws BusinessException,SystemException;
    @interface OrderChange{}
    
   /**
    * 修改服务实例
    * @author gaogang
    * @param info
    * @return
    * @ApiDocMethod BaaS-0200
    * @throws BusinessException
    * @throws SystemException
    */
    @POST
    @Path("/updateInstance") 
    public BaseResponse updateInstance(InstanceInfo info) throws BusinessException,SystemException;
    
    
    
    
    
    
}
