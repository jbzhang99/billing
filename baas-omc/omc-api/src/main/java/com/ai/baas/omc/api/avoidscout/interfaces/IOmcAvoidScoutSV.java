package com.ai.baas.omc.api.avoidscout.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.omc.api.avoidscout.params.OmcAvoidScoutInfoVO;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 信控免催停管理
 *
 * Date: 2017年3月2日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author wangjing19
 */
@Path("/omcAvoidScout")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IOmcAvoidScoutSV {

	/**
	 * 新增信控免催停
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangjing19
	 * @ApiDocMethod
	 * @ApiCode BaaS_0028
	 */
	@POST
    @Path("/addAvoidScout")
	public BaseResponse addAvoidScout(OmcAvoidScoutInfoVO vo) throws BusinessException, SystemException;
	@interface addAvoidScout {
	}

	/**
	 * 编辑信控免催停
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangjing19
	 * @ApiDocMethod
	 * @ApiCode BaaS_0029
	 */
	@POST
    @Path("/updateAvoidScout")
	public BaseResponse updateAvoidScout(OmcAvoidScoutInfoVO vo) throws BusinessException, SystemException;
	@interface updateAvoidScout {
	}

	/**
	 * 删除信控免催停
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangjing19
	 * @ApiDocMethod
	 * @ApiCode BaaS_0030
	 */
	@POST
    @Path("/delAvoidScout")
	public BaseResponse delAvoidScout(OmcAvoidScoutInfoVO vo) throws BusinessException, SystemException;
	@interface delAvoidScout {
	}

}
