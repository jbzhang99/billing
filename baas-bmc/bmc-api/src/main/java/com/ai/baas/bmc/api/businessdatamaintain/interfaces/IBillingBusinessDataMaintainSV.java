package com.ai.baas.bmc.api.businessdatamaintain.interfaces;

import com.ai.baas.bmc.api.businessdatamaintain.params.BusinessDataImportRequest;
import com.ai.baas.bmc.api.businessdatamaintain.params.BusinessDataQueryRequest;
import com.ai.baas.bmc.api.businessdatamaintain.params.BusinessDataQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 计费业务数据格式维护
 *
 * Date: 2016年5月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/billingDataMaintain")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IBillingBusinessDataMaintainSV {

    /**
     * 计费业务数据格式导入
     * @param importRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL billingDataMaintain/businessDataImport
     */
    @Path("/businessDataImport")
    @POST
    BaseResponse businessDataImport(BusinessDataImportRequest importRequest) throws BusinessException,SystemException;
    @interface BusinessDataImport{}

    /**
     * 计费业务数据格式查询
     * @param businessDataQueryRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL billingDataMaintain/getDataFormatList
     */
    @Path("/getDataFormatList")
    @POST
    BusinessDataQueryResponse getDataFormatList(BusinessDataQueryRequest businessDataQueryRequest) throws BusinessException,SystemException;
    @interface GetDataFormatList{}

}
