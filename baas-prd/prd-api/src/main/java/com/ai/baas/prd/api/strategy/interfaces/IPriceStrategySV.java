package com.ai.baas.prd.api.strategy.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.prd.api.strategy.params.CheckPolicyParam;
import com.ai.baas.prd.api.strategy.params.DeleteStrategyParam;
import com.ai.baas.prd.api.strategy.params.PriceStrategyQueryReponse;
import com.ai.baas.prd.api.strategy.params.QueryDetailParam;
import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.api.strategy.params.StrategyAddParams;
import com.ai.baas.prd.api.strategy.params.StrategyDetailQueryReponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 定价策略
 * @author wangluyang
 *
 */
@Path("/priceStrategy")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IPriceStrategySV {

	/**
     * 增加定价策略
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceStrategy/addStrategy
     */
    @POST
    @Path("/addStrategy")
    BaseResponse addStrategy(StrategyAddParams vo) throws BusinessException, SystemException; 
    
    /**
     * 查询定价策略
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceStrategy/queryStrategy
     */
    @POST
    @Path("/queryStrategy")
    PriceStrategyQueryReponse queryStrategy(QueryParams params) throws BusinessException, SystemException;
    
    /**
     * 查询策略详细
     * @param 
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceStrategy/getStrategyDetail
     */
    @POST
    @Path("/getStrategyDetail")
    StrategyDetailQueryReponse getStrategyDetail(QueryDetailParam params) throws BusinessException, SystemException;

    /**
     * 删除定价策略
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceStrategy/deleteStrategy
     */
    @POST
    @Path("/deleteStrategy")
    BaseResponse deleteStrategy(DeleteStrategyParam vo) throws BusinessException, SystemException;
    
    /**
     * 验证策略名称重复
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceStrategy/checkExistPolicyName
     */
    @POST
    @Path("/checkExistPolicyName")
    BaseResponse checkExistPolicyName(CheckPolicyParam vo)throws BusinessException,SystemException;
}
