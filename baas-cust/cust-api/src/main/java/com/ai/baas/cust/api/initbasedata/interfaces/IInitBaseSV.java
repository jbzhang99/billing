package com.ai.baas.cust.api.initbasedata.interfaces;

import com.ai.baas.cust.api.initbasedata.params.InitBaseParam;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 用户资料的初始化
 *
 * Date: 2016年6月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author biancx
 */
@Path("/initBase")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IInitBaseSV {
    /**
     * 重批价服务
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author biancx
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL initBase/initBaseData
     */
    @Path("/initBaseData")
	 public BaseResponse initBaseData(InitBaseParam param) throws BusinessException,SystemException;
}
