package com.ai.runner.center.mmp.api.manager.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.runner.center.mmp.api.manager.param.SMTemplateInfoNotify;

/**
 * 
 * @author linhan20150911
 */
@Path("/mmpmanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface MMPManager {
    /**
     * 数据插入到ums_msg_service表中
     * 
     * @RestRelativeURL mmpmanage/saveUmsMsgService
     */
    @POST
    @Path("/saveUmsMsgService")
    String saveUmsMsgService(String param);

    /**
     * 删除ums_msg_service表中的数据
     * 
     * @RestRelativeURL mmpmanage/delUmsMsgService
     */
    @POST
    @Path("/delUmsMsgService")
    String delUmsMsgService(String param);

    /**
     * 查询ums_msg_service表中的数据
     * 
     * @RestRelativeURL mmpmanage/getUmsMsgService
     */
    @POST
    @Path("/getUmsMsgService")
    String getUmsMsgService(String param);

    /**
     * 数据插入到ums_msg_template表中
     * 
     * @RestRelativeURL mmpmanage/saveUmsMsgTemplate
     */
    @POST
    @Path("/saveUmsMsgTemplate")
    String saveUmsMsgTemplate(String param);

    /**
     * 短信模板申请接口
     * 
     * @param paramInfo
     * @return
     * @author KAI
     * @ApiDocMethod
     * @ApiCode MMP-0002
     * @RestRelativeURL mmpmanage/saveUmsMsgTemplate
     */
    @POST
    @Path("/saveUmsMsgTemplate")
    String saveUmsMsgTemplate(SMTemplateInfoNotify paramInfo) throws BusinessException;

    /**
     * 删除ums_msg_template表中的数据
     * 
     * @RestRelativeURL mmpmanage/delUmsMsgTemplate
     */
    @POST
    @Path("/delUmsMsgTemplate")
    String delUmsMsgTemplate(String param);

    /**
     * 查询ums_msg_template表中的数据
     * 
     * @RestRelativeURL mmpmanage/getUmsMsgTemplate
     */
    @POST
    @Path("/getUmsMsgTemplate")
    String getUmsMsgTemplate(String param);

    /**
     * 根据serviceId查询ums_msg_service表中的数据
     * 
     * @RestRelativeURL mmpmanage/getOneServiceByServiceId
     */
    @POST
    @Path("/getOneServiceByServiceId")
    String getOneServiceByServiceId(String serviceIdJson);

    /**
     * 根据sequenceId查询ums_msg_template表中的数据
     * 
     * @RestRelativeURL mmpmanage/getOneTemplateBySequenceId
     */
    @POST
    @Path("/getOneTemplateBySequenceId")
    String getOneTemplateBySequenceId(String sequenceIdJson);

    /**
     * 根据serviceId查询ums_msg_template表中的数据,看是否存在
     * 
     * @RestRelativeURL mmpmanage/existTemplateByServiceId
     */
    @POST
    @Path("/existTemplateByServiceId")
    boolean existTemplateByServiceId(String serviceIdJson);
}
