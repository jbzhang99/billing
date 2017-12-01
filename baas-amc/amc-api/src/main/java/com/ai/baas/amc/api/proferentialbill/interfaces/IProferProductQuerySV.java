package com.ai.baas.amc.api.proferentialbill.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.proferentialbill.params.ProferProductQueryVO;
import com.ai.baas.amc.api.proferentialbill.params.ProferProductResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账务优惠查询
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Path("/proferProductQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProferProductQuerySV {

	/**
	 * 查询优惠账务
	 * @param vo
	 * @return
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0013
     * @RestRelativeURL proferProductQuery/getProferBillPageInfo
	 */
    @POST
    @Path("/getProferBillPageInfo")
	PageInfo<ProferProductResponse> getProferBillPageInfo(ProferProductQueryVO vo);
	
	
}
