package com.ai.baas.ccp.api.subscribeinvalid2stopnotice.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.ccp.api.subscribeinvalid2stopnotice.params.SubscribeInvalid2StopNoticeRequest;
import com.ai.opt.base.vo.BaseResponse;

@Path("/subscribeinvalid2stopnotice")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ISubscribeInvalid2StopNoticeSV {
    /**
     * @RestRelativeURL subscribeinvalid2stopnotice/stopNotice
     */
    @POST
    @Path("/stopNotice")
    BaseResponse stopNotice(SubscribeInvalid2StopNoticeRequest request);
}
