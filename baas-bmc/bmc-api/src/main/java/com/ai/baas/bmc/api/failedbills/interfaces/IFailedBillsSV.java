package com.ai.baas.bmc.api.failedbills.interfaces;

import com.ai.baas.bmc.api.failedbills.params.BillIdList;
import com.ai.baas.bmc.api.failedbills.params.FailedBillDetail;
import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedBillVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/failedBills")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IFailedBillsSV {
    /**
     * 查询错单
     *
     * @param queryVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedBill-00001
     * @RestRelativeURL failedBills/queryFailedBills
     */
    @POST
    @Path("/queryFailedBills")
    PageInfoResponse<FailedBillVo> queryFailedBills(FailedBillQueryVo queryVo) throws BusinessException,SystemException;

    /**
     * 查询错单
     *
     * @param queryVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedBill-00002
     * @RestRelativeURL failedBills/getFailedBillDetail
     */
    @POST
    @Path("/getFailedBillDetail")
    FailedBillDetail getFailedBillDetail(FailedBillVo queryVo) throws BusinessException,SystemException;

    /**
     * 重发编辑后错单
     *
     * @param billVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedBill-00003
     * @RestRelativeURL failedBills/resendFailedBill
     */
    @POST
    @Path("/resendFailedBill")
    BaseResponse resendFailedBill(FailedBillVo billVo) throws BusinessException,SystemException;

    /**
     * 批量重发错单
     *
     * @param idList
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedBill-00004
     * @RestRelativeURL failedBills/batchResendBill
     */
    @POST
    @Path("/batchResendBill")
    BaseResponse batchResendBill(BillIdList idList) throws BusinessException,SystemException;

    /**
     * 批量删除错单
     *
     * @param idList
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedBill-00005
     * @RestRelativeURL failedBills/batchDeleteBill
     */
    @POST
    @Path("/batchDeleteBill")
    BaseResponse batchDeleteBill(BillIdList idList) throws BusinessException,SystemException;

}
