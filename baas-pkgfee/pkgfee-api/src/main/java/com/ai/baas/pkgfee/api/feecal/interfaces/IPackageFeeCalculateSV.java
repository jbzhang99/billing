package com.ai.baas.pkgfee.api.feecal.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.pkgfee.api.feecal.params.ChangeConfigParam;
import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;
import com.ai.baas.pkgfee.api.feecal.params.RenewalParam;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 包费计算服务(包年包月费用计算)
 * @author majun
 *
 */
@Path("/pkgFeeCal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IPackageFeeCalculateSV {

	/**
     * 新增包费计算
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author majun
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL pkgFeeCal/addFeeCal
     */
    @POST
    @Path("/addFeeCal")
	BaseResponse addFeeCal(FeeCalAddParam param) throws BusinessException, SystemException;
	
	
    /**
     * 变更配置包费计算
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author majun
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL pkgFeeCal/changeConfigFeeCal
     */
    @POST
    @Path("/changeConfigFeeCal")
	BaseResponse changeConfigFeeCal(ChangeConfigParam param) throws BusinessException, SystemException;
    
    /**
     * 续费包费计算
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author majun
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL pkgFeeCal/renewalFeeCal
     */
    @POST
    @Path("/renewalFeeCal")
	BaseResponse renewalFeeCal(RenewalParam param) throws BusinessException, SystemException;
}
