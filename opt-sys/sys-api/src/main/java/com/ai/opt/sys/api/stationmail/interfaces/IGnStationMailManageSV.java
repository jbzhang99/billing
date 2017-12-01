package com.ai.opt.sys.api.stationmail.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sys.api.stationmail.param.StationMails;

/**
 * 站内信管理服务.<br>
 *
 * Date: 2016年6月12日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/stationmailmanage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IGnStationMailManageSV {

	/**
     * 站内信发送保存（运维）
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL stationmailmanage/saveStationMails
     */
    @GET
    @Path("/saveStationMails")
    BaseResponse saveStationMails(StationMails stationMails) throws BusinessException,SystemException;
    @interface SaveStationMails{}
}
