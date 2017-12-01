package com.ai.baas.prd.api.element.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.ai.baas.prd.api.element.params.BaseSpecResponse;
import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.CheckPolicyParam;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.ElementDetailRequireResult;
import com.ai.baas.prd.api.element.params.ElementDetailRequireVO;
import com.ai.baas.prd.api.element.params.ElementIncreaseVO;
import com.ai.baas.prd.api.element.params.ElementRequireResult;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.element.params.SpecTypeQueryReq;
import com.ai.baas.prd.api.element.params.UpdateByProductVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/priceElement")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IPriceElementSV {

    /**
     * 查询定价元素
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/searchElement
     */
    @POST
    @Path("/searchElement")
    ElementRequireResult searchElement(ElementRequireVO vo) throws BusinessException, SystemException;
    
    /**
     * 查询定价元素
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/searchElementDetail
     */
    @POST
    @Path("/searchElementDetail")
    ElementDetailRequireResult searchElementDetail(ElementDetailRequireVO vo) throws BusinessException, SystemException;
    
    
    /**
     * 新增定价元素
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/addElement
     */
    @POST
    @Path("/addElement")
    BaseResponse addElement(ElementIncreaseVO vo)throws BusinessException, SystemException;
    /**
     * 删除定价元素
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/deleteElement
     */
    @POST
    @Path("/deleteElement")
    BaseResponse deleteElement(ElementDeleteVO vo) throws BusinessException, SystemException;
    /**
     * 修改定价元素
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/alterElement
     */
    @POST
    @Path("/alterElement")
    BaseResponse alterElement(ElementIncreaseVO vo) throws BusinessException, SystemException;
    /**
     * 判断子产品是否存在
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/checkElement
     */
    @POST
    @Path("/checkElement")
    BaseResponse checkElement(CheckCategoryId vo)throws BusinessException,SystemException;
    
    /**
     * 校验产品类目信息表更新的子产品
     * @param vo
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wk16
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL priceElement/updateFromCategoryInfo
     */
    @POST
    @Path("/updateFromCategoryInfo")
    BaseResponse updateFromCategoryInfo(UpdateByProductVo vo)throws BusinessException, SystemException;
    
    /**
	 * 查询产品规格
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL priceElement/querySpecTypeList
	 */
	 @POST
	 @Path("/querySpecTypeList")
	public BaseSpecResponse querySpecTypeList(SpecTypeQueryReq req) throws BusinessException, SystemException;
	 
	    /**
	     * 策略更新通知接口
	     * @param vo
	     * @return 000000成功；其他失败
	     * @throws BusinessException
	     * @throws SystemException
	     * @author wk16
	     * @ApiDocMethod
	     * @ApiCode
	     * @RestRelativeURL priceElement/updateFromPolicy
	     */
	    @POST
	    @Path("/updateFromPolicy")
	    BaseResponse updateFromPolicy(ConvertParams vo)throws BusinessException, SystemException;
	    
	    /**
	     * 判断是否已关联定价策略
	     * @param vo
	     * @return 000000成功；其他失败
	     * @throws BusinessException
	     * @throws SystemException
	     * @author wangly8
	     * @ApiDocMethod
	     * @ApiCode
	     * @RestRelativeURL priceElement/checkExistPolicyId
	     */
	    @POST
	    @Path("/checkExistPolicyId")
	    BaseResponse checkExistPolicyId(CheckPolicyParam vo)throws BusinessException,SystemException;
//	    /**
//	     * 模型转换接口
//	     * @param vo
//	     * @return 000000成功；其他失败
//	     * @throws BusinessException
//	     * @throws SystemException
//	     * @author wk16
//	     * @ApiDocMethod
//	     * @ApiCode
//	     * @RestRelativeURL priceElement/convertToPriceMaking
//	     */
//	    @POST
//	    @Path("/convertToPriceMaking")
//        public BaseResponse convertToPriceMaking(ConvertParams vo)throws BusinessException, SystemException;
}
