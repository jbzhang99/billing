package com.ai.baas.prd.api.pricemaking.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;


@Path("/priceMaking")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IPriceMakingSV {

	
	/**
     * 增加定价信息
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author majun
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceMaking/addPriceMaking
     */
    @POST
    @Path("/addPriceMaking")
	BaseResponse addPriceMaking(PriceMakingAddParam param) throws BusinessException, SystemException;
	
    /**
     * 修改定价信息
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author majun
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceMaking/modifyPriceMaking
     */
    @POST
    @Path("/modifyPriceMaking")
	BaseResponse modifyPriceMaking(PriceMakingAddParam param) throws BusinessException, SystemException;
	
    /**
     * 修改定价信息
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author majun
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceMaking/deletePriceMaking
     */
    @POST
    @Path("/deletePriceMaking")
    BaseResponse deletePriceMaking(PriceMakingDelParam param) throws BusinessException, SystemException;
}
