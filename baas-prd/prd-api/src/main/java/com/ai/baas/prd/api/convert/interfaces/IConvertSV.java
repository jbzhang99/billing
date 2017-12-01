package com.ai.baas.prd.api.convert.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.prd.api.convert.params.ConvertParams;
import com.ai.baas.prd.api.convert.params.PolicyParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
@Path("/convert")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IConvertSV {
    /**
     * 模型转换接口
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessExceptiond
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL convert/convertToPriceMaking
     */
    @POST
    @Path("/convertToPriceMaking")
    public BaseResponse increaseConvert(ConvertParams vo)throws BusinessException, SystemException;
    
    /**
     * 根据产品策略更新
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessExceptiond
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL convert/updateByPolicy
     */
    @POST
    @Path("/updateByPolicy")
    public BaseResponse updateByPolicy(ConvertParams vo)throws BusinessException, SystemException;
    /**
     * 根据产品策略删除
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessExceptiond
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL convert/delByPolicy
     */
    @POST
    @Path("/delByPolicy")
    public BaseResponse delByPolicy(ConvertParams vo)throws BusinessException, SystemException;

    
}
