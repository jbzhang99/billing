package com.ai.baas.bmc.api.feeReBatch.interfaces;

import com.ai.baas.bmc.api.feeReBatch.params.FeeParamPagerResponse;
import com.ai.baas.bmc.api.feeReBatch.params.FeeReBatchCriteria;
import com.ai.baas.bmc.api.feeReBatch.params.FeeReBatchParam;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 费用重批相关服务
 * @author wangluyang
 *
 */
@Path("/feeReBatch")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IFeeReBatchSV {

	/**
     * 查询费用
     *
     * @param criteria
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode bmc-feerebatch-0001
     * @RestRelativeURL feeReBatch/queryFeeReBatch
     */
	@Path("/queryFeeReBatch")
    @POST
	FeeParamPagerResponse queryFeeReBatch(FeeReBatchCriteria criteria);

    /**
     * 费用重批
     *
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode bmc-feerebatch-00002
     * @RestRelativeURL feeReBatch/batchResendFee
     */
    @Path("/batchResendFee")
    @POST
    BaseResponse batchResendFee(FeeReBatchParam param);
}
