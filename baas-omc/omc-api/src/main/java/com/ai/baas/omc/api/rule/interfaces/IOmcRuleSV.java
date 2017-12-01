package com.ai.baas.omc.api.rule.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.omc.api.rule.params.GetRuleInfoParam;
import com.ai.baas.omc.api.rule.params.GetRuleResponse;
import com.ai.baas.omc.api.rule.params.OmcRuleInfoVO;
import com.ai.baas.omc.api.rule.params.OmcRuleResponse;
import com.ai.baas.omc.api.rule.params.QueryInfoParam;
import com.ai.baas.omc.api.rule.params.QueryRuleResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 信控规则管理
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author bixy
 */

@Path("/OmcRule")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IOmcRuleSV {

	/**
	 * 增加信控规则
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author bixy
	 * @ApiDocMethod
	 * @ApiCode OMC_0001
	 * @RestRelativeURL omcrule/addRule
	 */
	@POST
    @Path("/addRule")
	public OmcRuleResponse addRule(OmcRuleInfoVO vo) throws BusinessException, SystemException;
	@interface addRule {
	}

	/**
	 * 编辑信控规则
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author bixy
	 * @ApiDocMethod
	 * @ApiCode OMC_0002
	 * @RestRelativeURL omcrule/updateRule
	 */
	@POST
    @Path("/updateRule")
	public OmcRuleResponse updateRule(OmcRuleInfoVO vo) throws BusinessException, SystemException;
	@interface updateRule {
	}

	/**
	 * 删除信控规则
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author bixy
	 * @ApiDocMethod
	 * @ApiCode OMC_0003
	 * @RestRelativeURL omcrule/delRule
	 */
	@POST
    @Path("/delRule")
	public BaseResponse delRule(OmcRuleInfoVO vo) throws BusinessException, SystemException;
	@interface delRule {
	}

	/**
	 * 查询信控规则
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author bixy
	 * @ApiDocMethod
	 * @ApiCode OMC_0004
	 * @RestRelativeURL omcrule/queryRule
	 */
	@POST
    @Path("/queryRule")
	public QueryRuleResponse queryRule(QueryInfoParam param) throws BusinessException, SystemException;

	/**
	 * 查询某个信控规则
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author bixy
	 * @ApiDocMethod
	 * @ApiCode OMC_0005
	 * @RestRelativeURL omcrule/getRule
	 */
	@POST
    @Path("/getRule")
	public GetRuleResponse getRule(GetRuleInfoParam param) throws BusinessException, SystemException;
}
