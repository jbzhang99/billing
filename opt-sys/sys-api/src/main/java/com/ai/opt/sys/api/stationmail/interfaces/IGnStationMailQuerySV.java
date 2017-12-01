package com.ai.opt.sys.api.stationmail.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.stationmail.param.StationMailQueryRequest;
import com.ai.opt.sys.api.stationmail.param.StationMailVo;
import com.ai.opt.sys.api.stationmail.param.StationMailsPageQueryVo;

/**
 * 站内信查询服务.<br>
 *
 * Date: 2016年6月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/stationmailquery")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IGnStationMailQuerySV {

	/**
     * 分页查询已发站内信（运维）
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL stationmailquery/getStationMails
     */
    @GET
    @Path("/getStationMails")
    PageInfo<StationMailVo> getStationMails(StationMailsPageQueryVo mailsPageQueryVo) throws BusinessException,SystemException;
    @interface GetStationMails{}

    /**
     * 查询站内信详情
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL stationmailquery/queryStationMailDetail
     */
    @GET
    @Path("/queryStationMailDetail")
    StationMailVo queryStationMailDetail(StationMailQueryRequest mailQueryRequest) throws BusinessException,SystemException;
    @interface QueryStationMailDetail{}
}
