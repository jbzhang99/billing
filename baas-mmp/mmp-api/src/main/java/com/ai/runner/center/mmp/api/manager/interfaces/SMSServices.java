package com.ai.runner.center.mmp.api.manager.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.runner.center.mmp.api.manager.param.SMDataInfoNotify;

/**
 * 
 * Date: 2015年12月4日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author KAI
 */
@Path("/smsservices")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface SMSServices {

    /**
     * 
     * @param param
     * @return
     * @throws CallerException
     * @author KAI
     * @ApiDocMethod
     * @RestRelativeURL smsservices/dataInputString
     */
    @POST
    @Path("/dataInputString")
    String dataInput(String param) throws BusinessException;

    /**
     * 短信数据信息通知接口
     * 
     * @param param
     * @return
     * @throws CallerException
     * @author KAI
     * @ApiDocMethod
     * @RestRelativeURL smsservices/dataInput
     */
    @POST
    @Path("/dataInput")
    String dataInput(SMDataInfoNotify paramInfo) throws BusinessException;
}
