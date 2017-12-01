package com.ai.baas.abm.api.account.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.abm.api.account.params.AmcResBookVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 入账记录
 * @author wangluyang
 *
 */
@Path("/accountRecord")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IAccountRecordSV {

	/**
     * 保存入账记录服务
     * @param bookVo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL accountRecord/saveAccountRecord
     */
    @POST
    @Path("/saveAccountRecord")
	BaseResponse saveAccountRecord(AmcResBookVo bookVo)throws BusinessException,SystemException;
    @interface saveAccountRecord{}
    
    /**
     * 清理3个月前的失效记录
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL accountRecord/clearExpireAccountRecord
     */
    @POST
    @Path("/clearExpireAccountRecord")
    BaseResponse clearExpireAccountRecord()throws BusinessException,SystemException;
}
