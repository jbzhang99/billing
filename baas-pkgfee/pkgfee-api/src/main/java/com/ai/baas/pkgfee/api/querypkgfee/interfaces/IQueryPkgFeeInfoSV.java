package com.ai.baas.pkgfee.api.querypkgfee.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.pkgfee.api.querypkgfee.params.PkgfeeQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 包年包月费用查询服务
 * 
 * Date: 2017年2月22日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author wangjing19
 */
@Path("/queryPkgFeeInfo")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IQueryPkgFeeInfoSV {

    /**
     * 查询费用信息
     * 
     * @param pkgFeeQueryRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangjing19
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL queryPkgFeeInfo/getPkgFeeQueInfo
     */
    @POST
    @Path("/getPkgFeeQueInfo")
    BaseResponse getPkgFeeQueInfo(PkgfeeQueryRequest pkgFeeQueryRequest) throws BusinessException,
            SystemException;

    @interface getPkgFeeQueInfo {
    }
}
