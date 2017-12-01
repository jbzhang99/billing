package com.ai.baas.smc.api.cacherefresh.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/cacherefresh")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICacheRefreshSV {
	/**
	 * 刷新mcs缓存
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author mayt
	 * @ApiDocMethod
	 * @RestRelativeURL cacherefresh/refresh
	 */
	@Path("/refresh")
	@POST
	BaseResponse refresh() throws BusinessException,SystemException;
}
