package com.ai.baas.amc.api.virtualdeduct.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;


/**
 * 模拟销账后的余额查询 <br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/balanceQuery")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBalanceQuerySV {

	/**
	 * 余额查询.<br>
	 * 按照指定条件模拟销账后的余额,欠费,缴费信息<br>
	 *
	 * @param owner 模拟销账条件
	 * @return 模拟销账后的余额,欠费,缴费信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 * @ApiCode AMC_0012
     * @RestRelativeURL balanceQuery/cancelAccountProcess
	 */
    @POST
    @Path("/cancelAccountProcess")
	VdRealTimeBalance cancelAccountProcess(BalanceQueryRequest owner) throws BusinessException, SystemException;
	
}
