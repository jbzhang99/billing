package com.ai.baas.bmc.api.detailbill.interfaces;

import com.ai.baas.bmc.api.detailbill.params.DetailBillResponse;
import com.ai.baas.bmc.api.detailbill.params.QueryBillRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 详单查询相关服务
 *
 * Date: 2016年5月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Path("/detailBill")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IDetailBillQuerySV {

	/**
	 * 详单查询
	 * @param request
	 * @return
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL detailBill/getDetailBill
	 */
	@Path("/getDetailBill")
	@POST
	public DetailBillResponse getDetailBill(QueryBillRequest request) throws BusinessException,SystemException;
	@interface GetDetailBill{}
}
