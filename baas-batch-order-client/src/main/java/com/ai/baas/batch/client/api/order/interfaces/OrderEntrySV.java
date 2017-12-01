package com.ai.baas.batch.client.api.order.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/citic")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface OrderEntrySV {

    /**
     * 订单处理接口-中信
     * 
     * @param json
     * @return
     * @author wk
     * @ApiDocMethod BaaS-0111
     * @RestRelativeURL citic/instanceOrder
     */
    @POST
    @Path("/instanceOrder")
    public BaseResponse instanceOrder(String json)throws BusinessException,SystemException;
    @interface OrderEntry{}
}
