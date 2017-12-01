package com.ai.baas.pkgfee.api.pkgunsubscribe.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeRequest;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 包费退订服务
 * 
 * Date: 2017年5月22日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author wangjing19
 */
@Path("/pkgUnsubscribe")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IPkgUnsubscribeSV {

    /**
     * 包费退订服务
     * 
     * @param PkgUnsubscribeRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangjing19
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL pkgUnsubscribe/unsubscribe
     */
    @POST
    @Path("/unsubscribe")
    PkgUnsubscribeResponse unsubscribe(PkgUnsubscribeRequest request) throws BusinessException,
            SystemException;

    @interface unsubscribe {
    }
}
