package com.ai.baas.bmc.srv.api.entry.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 计费服务入口类 <br>
 *
 * @author majun
 */
@Path("/billingService")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillingEntrySV {

	/**
	 * 通过服务提供计费功能
	 * 
	 * @param obj 传输的具体数据信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author majun
	 * @ApiCode bmc_srv_0001
	 * @ApiDocMethod
	 * @RestRelativeURL billingService/doBilling
	 */
	@POST
	@Path("/doBilling")
	BaseResponse doBilling(String billingMessage) throws BusinessException, SystemException;
	
}
