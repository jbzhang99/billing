package com.ai.baas.amc.api.amcdrbillsubject.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.amcdrbillsubject.param.AmcDrBillSubjectRelatedParamVO;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/amcDrBillSubjectManage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IAmcDrBillSubjectManageSV {
	/**
	 * 根据tenantid、billSubjectId、list<drSubject>删除当前的关联信息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode
     * @RestRelativeURL amcDrBillSubjectManage/delAmcDrBillSubject
	 */
    @POST
    @Path("/delAmcDrBillSubject")
	public BaseResponse delAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo) throws BusinessException,SystemException;
	@interface delAmcDrBillSubject{}
	/**
	 * 添加关联信息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode
     * @RestRelativeURL amcDrBillSubjectManage/addAmcDrBillSubject
	 */

    @POST
    @Path("/addAmcDrBillSubject")
	public BaseResponse addAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo) throws BusinessException,SystemException;
	@interface addAmcDrBillSubject{}
}
