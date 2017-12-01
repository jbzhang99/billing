package com.ai.baas.bmc.api.failedbills.interfaces;

import com.ai.baas.bmc.api.failedbills.params.*;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/failedOrders")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IOrderFailedBillSV {
    /**
     * 查询错单列表
     *
     * @param queryVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedOrders-00001
     * @RestRelativeURL failedOrders/queryFailedOrders
     */
    @POST
    @Path("/queryFailedOrders")
    PageInfoResponse<FailedOrderVo> queryFailedOrders(FailedOrderQueryVo queryVo) throws BusinessException,SystemException;

    /**
     * 查询错单详情
     *
     * @param queryVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedOrders-00002
     * @RestRelativeURL failedOrders/getFailedOrderDetail
     */
    @POST
    @Path("/getFailedOrderDetail")
    FailedOrderDetail getFailedOrderDetail(FailedOrderVo queryVo) throws BusinessException,SystemException;

    /**
     * 重发编辑后错单
     *
     * @param orderVo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode bmc-failedOrders-00003
     * @RestRelativeURL failedOrders/resendFailedOrder
     */
    @POST
    @Path("/resendFailedOrder")
    BaseResponse resendFailedOrder(FailedOrderVo orderVo) throws BusinessException,SystemException;
}
