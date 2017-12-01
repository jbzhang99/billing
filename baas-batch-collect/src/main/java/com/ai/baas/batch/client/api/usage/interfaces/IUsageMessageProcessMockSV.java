package com.ai.baas.batch.client.api.usage.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.batch.client.api.usage.params.UsageRecord;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/usage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IUsageMessageProcessMockSV {

    /**
     * 使用量模拟测试接口
     *
     * @param record
     * @return
     * @author wangyx13
     * @ApiDocMethod BaaS-0112
     * @RestRelativeURL usage/process
     */
    @POST
    @Path("/process")
    BaseResponse process(UsageRecord record) throws BusinessException,SystemException;
}
