package com.ai.baas.amc.api.billmanage.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.billmanage.params.BillListResponse;
import com.ai.baas.amc.api.billmanage.params.BillQueryVO;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单科目查询
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */

@Path("/billQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillQuerySV {

	/**
	 * 账单科目查询
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0011
     * @RestRelativeURL billQuery/queryBillList
	 */
    @POST
    @Path("/queryBillList")
	PageInfo<BillListResponse> queryBillList(BillQueryVO vo) throws BusinessException,SystemException;
}
