package com.ai.baas.amc.api.bill.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.api.bill.params.BillSearchResponse;
import com.ai.baas.amc.api.bill.params.BillTotalRequest;
import com.ai.baas.amc.api.bill.params.BillTotalResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单查询服务 <br>
 *
 * Date: 2016年7月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/billSearch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IBillSearchSV {
    /**
     * 账单查询
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL billSearch/queryBillList
     */
    @POST
    @Path("/queryBillList")
    BillSearchResponse queryBillList(BillSearchRequest vo) throws BusinessException,SystemException;
    /**
     * 总账查询
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author LiangMeng
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL billSearch/queryBillTotal
     */
    @POST
    @Path("/queryBillTotal")
    BillTotalResponse queryBillTotal(BillTotalRequest vo) throws BusinessException,SystemException;

}
