package com.ai.baas.amc.api.billmanage.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.billmanage.params.BillDelVO;
import com.ai.baas.amc.api.billmanage.params.BillInfoVO;
import com.ai.baas.amc.api.billmanage.params.BillResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

@Path("/billManage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillManageSV {

	/**
	 * 添加账单科目
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0025
     * @RestRelativeURL billManage/addBill
	 */
	@interface AddBill{}
	@POST
    @Path("/addBill")
	BillResponse addBill(BillInfoVO vo) throws BusinessException,SystemException;
	/**
	 * 修改账单科目
	 * @param vo
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0014
     * @RestRelativeURL billManage/updateBill
	 */
	@interface UpdateBill{}

    @POST
    @Path("/updateBill")
	void updateBill(BillInfoVO vo) throws BusinessException,SystemException;
	/**
	 * 删除账单科目
	 * @param vo
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0015
     * @RestRelativeURL billManage/delBill
	 */
	@interface DelBill{}

    @POST
    @Path("/delBill")
	void delBill(BillDelVO vo) throws BusinessException,SystemException;
}
