package com.ai.baas.amc.api.amcdrbillsubject.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectResponse;
import com.ai.baas.amc.api.amcdrbillsubject.param.QueryDrSubjectParamVO;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

@Path("/amcDrBillSubjectQuery")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IAmcDrBillSubjectQuerySV {
	/**
	 * 根据租户id和账单id查询已关联的详单id列表
	 * @param queryDrSubjectParamVo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode
     * @RestRelativeURL amcDrBillSubjectQuery/queryDrSubjectIdList
	 */
    @POST
    @Path("/queryDrSubjectIdList")
	public List<DrSubjectResponse> queryDrSubjectIdList(QueryDrSubjectParamVO queryDrSubjectParamVo) throws BusinessException,SystemException;
	@interface queryDrSubjectIdList{}
}
