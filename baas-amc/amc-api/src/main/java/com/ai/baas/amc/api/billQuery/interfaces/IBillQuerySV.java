package com.ai.baas.amc.api.billQuery.interfaces;



import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.billQuery.params.BillDetailInput;
import com.ai.baas.amc.api.billQuery.params.BillDetailOutput;
import com.ai.baas.amc.api.billQuery.params.BillInput;
import com.ai.baas.amc.api.billQuery.params.BillOutput;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

@Path("/billQueryb")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillQuerySV {
   
    /**
     * 账单查询接口
     * @param BillQueryParams
     * @return BaaS-000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangkai16
     * @ApiCode AMC_0100
     * @RestRelativeURL billQueryb/getBillInfo
     */
    @POST
    @Path("/getBillInfo")
    public BillOutput getBillInfo (BillInput record)throws BusinessException,SystemException;
    @interface GetBillInfo{}
    
    /**
     * 账单详情查询接口
     * @param BillQueryParams
     * @return BaaS-000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangkai16
     * @ApiCode AMC_0101
     * @RestRelativeURL billQueryb/getBillDetail
     */
    @POST
    @Path("/getBillDetail")
    public BillDetailOutput getBillDetail (BillDetailInput record)throws BusinessException,SystemException;
    @interface GetBillDetail{}
    
}
