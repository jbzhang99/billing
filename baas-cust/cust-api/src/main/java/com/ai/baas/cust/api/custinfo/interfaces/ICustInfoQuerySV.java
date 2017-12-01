package com.ai.baas.cust.api.custinfo.interfaces;

import com.ai.baas.cust.api.custinfo.params.CustInfoResponse;
import com.ai.baas.cust.api.custinfo.params.QueryCustInfoRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 详单查询服务
 *
 * Date: 2016年5月11日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Path("/cust/query")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICustInfoQuerySV {

	/**
	 * 详单查询
	 * @param param
	 * @return
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL cust/query/getCustInfos
	 */
	@Path("/getCustInfos")
	@POST
	CustInfoResponse getCustInfos(QueryCustInfoRequest param) throws BusinessException, SystemException;
    @interface GetCustInfos{}
    
    
}
